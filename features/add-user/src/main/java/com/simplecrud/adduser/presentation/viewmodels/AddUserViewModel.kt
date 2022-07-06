package com.simplecrud.adduser.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.simplecrud.adduser.domain.usecases.AddUserUseCase
import com.simplecrud.adduser.domain.usecases.AddUserValidateFieldsUseCase
import com.simplecrud.adduser.presentation.events.*
import com.simplecrud.adduser.presentation.mappers.AddUserMapperState
import com.simplecrud.adduser.presentation.viewstates.*
import com.simplecrud.base.domain.entities.Result
import com.simplecrud.base.domain.extensions.toResult
import com.simplecrud.base.presentation.BaseViewModel
import com.simplecrud.repositories.models.UserDataModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject

// Actions //

private sealed class AddUserAction
private object AddUserLoadedAction: AddUserAction()
private data class AddUserTextChangedAction(val text: String, val typeField: Int): AddUserAction()
private data class AddUserValidatedFormAction(val params: AddUserValidateFieldsUseCase.ValidateFieldsReturnParams): AddUserAction()
private object AddUserSuccessAction: AddUserAction()
private data class AddUserLoadingAction(val isLoading: Boolean): AddUserAction()
private data class AddUserErrorAction(
    val code: Int = 0,
    val errorMessage: String = ""
): AddUserAction()

// ViewModel //

class AddUserViewModel(
    private val addUserUseCase: AddUserUseCase,
    private val validateFieldsUseCase: AddUserValidateFieldsUseCase,
    private val mapperState: AddUserMapperState
): BaseViewModel() {

    // LiveData //

    private val _viewActions = PublishSubject.create<AddUserAction>()
    private val _addUserState =
        MutableLiveData<AddUserViewState>().apply { value = AddUserDefaultViewState }
    val addUserState: LiveData<AddUserViewState> = _addUserState

    val onCreateView = PublishSubject.create<Unit>()
    val didOnChangeText = PublishSubject.create<Pair<String, Int>>()
    val onTapButton = PublishSubject.create<Unit>()

    // Vars //

    private var lastState: AddUserState = AddUserState()

    // Methods //

    fun setupBindings() {
        addToDisposable(
            onCreateView
                .map { AddUserOnCreateViewEvent }
                .subscribe(::executeEvents)
        )
        addToDisposable(
            didOnChangeText
                .map { AddUserTextChangedEvent(it.first, it.second) }
                .subscribe(::executeEvents)
        )
        addToDisposable(
            onTapButton
                .map { AddUserValidateDataEvent }
                .subscribe(::executeEvents)
        )
        addToDisposable(
            _viewActions
                .subscribe(::executeActions)
        )
    }

    private fun executeEvents(event: AddUserEvent) {
        when (event) {
            is AddUserOnCreateViewEvent -> {
                _viewActions.onNext(AddUserLoadedAction)
            }
            is AddUserTextChangedEvent -> {
                _viewActions.onNext(AddUserTextChangedAction(event.text, event.type))
            }
            is AddUserValidateDataEvent -> {
                validateFields(lastState)
            }
        }
    }

    private fun executeActions(action: AddUserAction) {
        var viewState: AddUserViewState = AddUserDefaultViewState
        when (action) {
            is AddUserLoadedAction -> {
                viewState = AddUserDefaultViewState
            }
            is AddUserTextChangedAction -> {
                val state = mapperState.toMapField(
                    state = lastState,
                    text = action.text,
                    fieldType = action.typeField
                )
                viewState = AddUserTextChangedViewState(state)
                lastState = state
            }
            is AddUserValidatedFormAction -> {
                val state = mapperState.toMapValidation(
                    state = lastState,
                    isNameValid = action.params.isNameValid,
                    isBirthdateValid = action.params.isBirthdateValid,
                    isFormValid = action.params.isFormValid
                )
                viewState = AddUserFormValidationResultViewState(state)
                lastState = state
                if (lastState.isFormValid) {
                    addUser(state.name, state.birthdate)
                }
            }
            is AddUserSuccessAction -> {
                viewState = AddUserSuccessViewState(lastState)
            }
            is AddUserErrorAction -> {
                val state = mapperState.toMapError(
                    state = lastState,
                    errorMessage = action.errorMessage,
                    code = action.code
                )
                viewState = AddUserErrorViewState(state)
                lastState = state
            }
        }
        _addUserState.value = viewState
    }

    // Handlers //

    private fun handleAddUserResult(result: Result<Unit>) {
        when (result) {
            is Result.Success -> _viewActions.onNext(AddUserSuccessAction)
            is Result.Failure -> _viewActions.onNext(AddUserErrorAction(
                errorMessage = result.throwable.localizedMessage ?: "Error: handleAddUserResult",
                code = TypeError.SERVER_RESPONSE_ERROR.ordinal
            ))
        }
    }

    private fun handleValidateFieldsResult(result: Result<AddUserValidateFieldsUseCase.ValidateFieldsReturnParams>) {
        when (result) {
            is Result.Success -> _viewActions.onNext(AddUserValidatedFormAction(result.value))
            is Result.Failure -> _viewActions.onNext(AddUserErrorAction(
                errorMessage = result.throwable.message ?: "Error: handleValidateFieldsResult",
                code = TypeError.FIELD_VALIDATION.ordinal
            ))
        }
    }

    // Private methods //

    private fun validateFields(state: AddUserState) {
        addToDisposable(
            validateFieldsUseCase
                .invokeWith(name = state.name, birthdate = state.birthdate)
                .toResult()
                .subscribe(::handleValidateFieldsResult)

        )
    }

    private fun addUser(name: String, birthdate: String) {
        addToDisposable(
            addUserUseCase
                .invokeWith(UserDataModel(name = name, birthdate = birthdate))
                .doOnSubscribe { loadProgress(true) }
                .doOnError { loadProgress(false) }
                .doOnComplete { loadProgress(false) }
                .andThen(Single.just(Unit))
                .toResult()
                .subscribe(::handleAddUserResult)
        )
    }

    private fun loadProgress(show: Boolean) {
        _viewActions.onNext(AddUserLoadingAction(show))
    }

}