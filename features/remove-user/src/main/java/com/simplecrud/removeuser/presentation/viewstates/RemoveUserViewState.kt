package com.simplecrud.removeuser.presentation.viewstates

sealed class RemoveUserViewState
object RemoveUserDefaultViewState: RemoveUserViewState()
data class RemoveUserTextChangedViewState(val state: RemoveUserState): RemoveUserViewState()
data class RemoveUserSuccessViewState(val state: RemoveUserState): RemoveUserViewState()
data class RemoveUserErrorViewState(val state: RemoveUserState): RemoveUserViewState()