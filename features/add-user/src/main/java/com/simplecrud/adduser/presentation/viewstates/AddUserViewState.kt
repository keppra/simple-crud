package com.simplecrud.adduser.presentation.viewstates

sealed class AddUserViewState
object AddUserDefaultViewState: AddUserViewState()
data class AddUserTextChangedViewState(val state: AddUserState): AddUserViewState()
data class AddUserFormValidationResultViewState(val state: AddUserState): AddUserViewState()
data class AddUserSuccessViewState(val state: AddUserState): AddUserViewState()
data class AddUserErrorViewState(val state: AddUserState): AddUserViewState()