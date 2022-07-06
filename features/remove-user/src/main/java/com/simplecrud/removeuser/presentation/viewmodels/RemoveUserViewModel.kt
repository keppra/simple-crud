package com.simplecrud.removeuser.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.simplecrud.base.presentation.BaseViewModel
import com.simplecrud.base.domain.entities.Result
import com.simplecrud.base.domain.extensions.toResult
import com.simplecrud.removeuser.domain.RemoveUserUseCase
import com.simplecrud.removeuser.presentation.events.*
import com.simplecrud.removeuser.presentation.mappers.RemoveUserMapperState
import com.simplecrud.removeuser.presentation.viewstates.*
import com.simplecrud.repositories.models.UserDataModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject

// Actions //

private sealed class RemoveUserAction
private object RemoveUserLoadedAction: RemoveUserAction()
private data class RemoveUserTextChangedAction(val id: Int): RemoveUserAction()
private object RemoveUserSuccessAction: RemoveUserAction()
private data class RemoveUserLoadingAction(val isLoading: Boolean): RemoveUserAction()
private data class RemoveUserErrorAction(
    val code: Int = 0,
    val errorMessage: String = ""
): RemoveUserAction()

// ViewModel //

class RemoveUserViewModel(
    private val removeUserUseCase: RemoveUserUseCase,
    private val mapperState: RemoveUserMapperState
): BaseViewModel() {

    // LiveData //

    private val _viewActions = PublishSubject.create<RemoveUserAction>()
    private val _removeUserState =
        MutableLiveData<RemoveUserViewState>().apply { value = RemoveUserDefaultViewState }
    val removeUserState: LiveData<RemoveUserViewState> = _removeUserState

    val onCreateView = PublishSubject.create<Unit>()
    val didOnChangeText = PublishSubject.create<Int>()
    val onTapButton = PublishSubject.create<Unit>()

    // Vars //

    private var lastState: RemoveUserState = RemoveUserState()

    // Methods //

    fun setupBindings() {
        addToDisposable(
            onCreateView
                .map { RemoveUserOnCreateViewEvent }
                .subscribe(::executeEvents)
        )
        addToDisposable(
            didOnChangeText
                .map { RemoveUserTextChangedEvent(it) }
                .subscribe(::executeEvents)
        )
        addToDisposable(
            onTapButton
                .map { RemoveUserSendEvent }
                .subscribe(::executeEvents)
        )
        addToDisposable(
            _viewActions
                .subscribe(::executeActions)
        )
    }

    private fun executeEvents(event: RemoveUserEvent) {
        when (event) {
            is RemoveUserOnCreateViewEvent -> {
                _viewActions.onNext(RemoveUserLoadedAction)
            }
            is RemoveUserTextChangedEvent -> {
                _viewActions.onNext(RemoveUserTextChangedAction(event.id))
            }
            is RemoveUserSendEvent -> {
                removeUser(lastState.id)
            }
        }
    }

    private fun executeActions(action: RemoveUserAction) {
        var viewState: RemoveUserViewState = RemoveUserDefaultViewState
        when (action) {
            is RemoveUserLoadedAction -> {
                viewState = RemoveUserDefaultViewState
            }
            is RemoveUserTextChangedAction -> {
                val state = mapperState.toMapField(
                    state = lastState,
                    id = action.id
                )
                viewState = RemoveUserTextChangedViewState(state)
                lastState = state
            }
            is RemoveUserSuccessAction -> {
                viewState = RemoveUserSuccessViewState(lastState)
            }
            is RemoveUserErrorAction -> {
                val state = mapperState.toMapError(
                    state = lastState,
                    errorMessage = action.errorMessage,
                    code = action.code
                )
                viewState = RemoveUserErrorViewState(state)
                lastState = state
            }
        }
        _removeUserState.value = viewState
    }

    // Handlers //

    private fun handleRemoveUserResult(result: Result<Unit>) {
        when (result) {
            is Result.Success -> _viewActions.onNext(RemoveUserSuccessAction)
            is Result.Failure -> _viewActions.onNext(RemoveUserErrorAction(
                errorMessage = result.throwable.localizedMessage ?: "Error: handleRemoveUserResult",
                code = TypeError.SERVER_RESPONSE_ERROR.ordinal
            ))
        }
    }

    // Private methods //

    private fun removeUser(id: Int) {
        addToDisposable(
            removeUserUseCase
                .invokeWith(UserDataModel(id = id))
                .doOnSubscribe { loadProgress(true) }
                .doOnError { loadProgress(false) }
                .doOnComplete { loadProgress(false) }
                .andThen(Single.just(Unit))
                .toResult()
                .subscribe(::handleRemoveUserResult)
        )
    }

    private fun loadProgress(show: Boolean) {
        _viewActions.onNext(RemoveUserLoadingAction(show))
    }

}