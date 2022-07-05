package com.simplecrud.listusers.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.simplecrud.listusers.domain.usecases.GetUsersUseCase
import com.simplecrud.listusers.presentation.events.ListUsersEvent
import com.simplecrud.listusers.presentation.viewstates.ListUsersDefaultViewState
import com.simplecrud.listusers.presentation.viewstates.ListUsersState
import com.simplecrud.listusers.presentation.viewstates.ListUsersViewState
import io.reactivex.rxjava3.subjects.PublishSubject

// Actions //

sealed class ListUsersAction

// ViewModel //

class ListUsersViewModel(
    private val getUsersUseCase: GetUsersUseCase
) {

    // Rx & LiveData //

    private val _viewActions = PublishSubject.create<ListUsersAction>()
    private val _listUsersState =
        MutableLiveData<ListUsersViewState>().apply { value = ListUsersDefaultViewState }
    val mainState: LiveData<ListUsersViewState> = _listUsersState

    // Vars //

    private var lastState: ListUsersState = ListUsersState()

    // Core //

    fun setupBindings() {
        /*addToDisposable(
            viewOnCreate
                .map { MainOnCreateEvent }
                .subscribe(::executeEvents)
        )*/
    }

    private fun executeEvents(event: ListUsersEvent) {
        when (event) {

        }
    }

    private fun executeActions(action: ListUsersAction) {
        var viewState: ListUsersViewState = ListUsersDefaultViewState
        when (action) {

        }
        _listUsersState.value = viewState
    }

}