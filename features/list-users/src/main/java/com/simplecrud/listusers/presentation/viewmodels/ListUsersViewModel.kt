package com.simplecrud.listusers.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.simplecrud.base.domain.entities.Result
import com.simplecrud.base.domain.extensions.toResult
import com.simplecrud.base.presentation.BaseViewModel
import com.simplecrud.listusers.domain.usecases.GetUsersUseCase
import com.simplecrud.listusers.presentation.events.*
import com.simplecrud.listusers.presentation.mappers.ListUsersMapperPresentation
import com.simplecrud.listusers.presentation.mappers.ListUsersMapperState
import com.simplecrud.listusers.presentation.models.UsersPresentation
import com.simplecrud.listusers.presentation.viewstates.*
import io.reactivex.rxjava3.subjects.PublishSubject

// Actions //

private sealed class ListUsersAction
private data class ListUsersReceivedAction(val list: List<UsersPresentation>): ListUsersAction()
private data class ListUsersLoadingAction(val isLoading: Boolean): ListUsersAction()
private data class ListUsersErrorAction(
    val code: Int = 0,
    val errorMessage: String = ""
): ListUsersAction()

// ViewModel //

class ListUsersViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val mapperPresentation: ListUsersMapperPresentation,
    private val mapperState: ListUsersMapperState
): BaseViewModel() {

    // Rx & LiveData //

    private val _viewActions = PublishSubject.create<ListUsersAction>()
    private val _listUsersState =
        MutableLiveData<ListUsersViewState>().apply { value = ListUsersDefaultViewState }
    val listUsersState: LiveData<ListUsersViewState> = _listUsersState

    val onCreateView = PublishSubject.create<Unit>()
    val onRefreshList = PublishSubject.create<Unit>()

    // Vars //

    private var lastState: ListUsersState = ListUsersState()

    // Core //

    fun setupBindings() {
        addToDisposable(
            onCreateView
                .map { ListUsersOnCreateEvent }
                .subscribe(::executeEvents)
        )
        addToDisposable(
            onRefreshList
                .map { ListUsersOnRefreshListEvent }
                .subscribe(::executeEvents)
        )
        addToDisposable(
            _viewActions
                .subscribe(::executeActions)
        )
    }

    private fun executeEvents(event: ListUsersEvent) {
        when (event) {
            is ListUsersOnCreateEvent -> getUsers()
            is ListUsersOnRefreshListEvent -> getUsers()
        }
    }

    private fun executeActions(action: ListUsersAction) {
        var viewState: ListUsersViewState = ListUsersDefaultViewState
        when (action) {
            is ListUsersReceivedAction ->  {
                val state = mapperState.toMapList(
                    state = lastState,
                    list = action.list
                )
                viewState = ListUsersListReceivedViewState(state)
                lastState = state
            }
            is ListUsersLoadingAction -> {
                val state = mapperState.toMapLoading(
                    state = lastState,
                    isLoading = action.isLoading
                )
                viewState = ListUsersShowProgressViewState(state)
                lastState = state
            }
            is ListUsersErrorAction -> {
                val state = mapperState.toMapError(
                    state = lastState,
                    errorMessage = action.errorMessage,
                    code = action.code
                )
                viewState = ListUsersErrorViewState(state)
                lastState = state
            }
        }
        _listUsersState.value = viewState
    }

    // Handlers //

    private fun handleGetUsersResult(result: Result<List<UsersPresentation>>) {
        when (result) {
            is Result.Success -> _viewActions.onNext(ListUsersReceivedAction(result.value))
            is Result.Failure -> _viewActions.onNext(ListUsersErrorAction(
                errorMessage = result.throwable.message ?: "Error: handleGetUsersResult",
                code = TypeError.ERROR_LOADING_USERS_LIST.ordinal
            ))
        }
    }

    // Private methods //

    private fun getUsers() {
        addToDisposable(
            getUsersUseCase
                .invoke()
                .doOnSubscribe { loadProgress(true) }
                .doOnError { loadProgress(false) }
                .doOnSuccess { loadProgress(false) }
                .map { mapperPresentation.toMap(it) }
                .toResult()
                .subscribe(::handleGetUsersResult)
        )
    }

    private fun loadProgress(show: Boolean) {
        _viewActions.onNext(ListUsersLoadingAction(show))
    }

}