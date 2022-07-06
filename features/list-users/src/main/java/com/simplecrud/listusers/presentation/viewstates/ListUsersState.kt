package com.simplecrud.listusers.presentation.viewstates

import com.simplecrud.commonui.state.ErrorState
import com.simplecrud.commonui.state.ErrorTypeState
import com.simplecrud.listusers.presentation.models.UsersPresentation

data class ListUsersState(
    var list: List<UsersPresentation> = listOf(),
    var isLoading: Boolean = false,
    var error: ErrorListUsersState? = null
)

data class ErrorListUsersState(
    override val code: TypeError,
    override val errorMessage: String
): ErrorState<TypeError> {
    companion object: ErrorTypeState<TypeError> {
        override fun getTypeError(code: Int): TypeError =
            when (code) {
                TypeError.DEFAULT.ordinal -> TypeError.DEFAULT
                TypeError.ERROR_LOADING_USERS_LIST.ordinal -> TypeError.ERROR_LOADING_USERS_LIST
                else -> TypeError.DEFAULT
            }
    }
}

enum class TypeError {
    DEFAULT,
    ERROR_LOADING_USERS_LIST
}