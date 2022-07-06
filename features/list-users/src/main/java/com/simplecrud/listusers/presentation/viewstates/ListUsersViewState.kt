package com.simplecrud.listusers.presentation.viewstates

sealed class ListUsersViewState
object ListUsersDefaultViewState: ListUsersViewState()
data class ListUsersListReceivedViewState(val state: ListUsersState): ListUsersViewState()
data class ListUsersShowProgressViewState(val state: ListUsersState): ListUsersViewState()
data class ListUsersErrorViewState(val state: ListUsersState): ListUsersViewState()

