package com.simplecrud.listusers.presentation.viewstates

import com.simplecrud.commonui.state.ErrorState
import com.simplecrud.commonui.state.ErrorTypeState
import com.simplecrud.listusers.presentation.models.UsersPresentation

data class ListUsersState(
    var services: List<UsersPresentation> = listOf(),
    var error: ErrorServiceState? = null
)

data class ErrorServiceState(
    override val code: TypeError,
    override val errorMessage: String
): ErrorState<TypeError> {
    companion object: ErrorTypeState<TypeError> {
        override fun getTypeError(code: Int): TypeError =
            when (code) {
                TypeError.DEFAULT.ordinal -> TypeError.DEFAULT
                TypeError.API_FAILURE.ordinal -> TypeError.API_FAILURE
                else -> TypeError.DEFAULT
            }
    }
}

enum class TypeError {
    DEFAULT,
    API_FAILURE
}