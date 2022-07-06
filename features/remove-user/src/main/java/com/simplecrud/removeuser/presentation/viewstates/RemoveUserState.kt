package com.simplecrud.removeuser.presentation.viewstates

import com.simplecrud.commonui.state.ErrorState
import com.simplecrud.commonui.state.ErrorTypeState

data class RemoveUserState(
    var id: Int = 0,
    var isLoading: Boolean = false,
    var error: ErrorRemoveUserState? = null
)

data class ErrorRemoveUserState(
    override val code: TypeError,
    override val errorMessage: String
): ErrorState<TypeError> {
    companion object: ErrorTypeState<TypeError> {
        override fun getTypeError(code: Int): TypeError =
            when (code) {
                TypeError.DEFAULT.ordinal -> TypeError.DEFAULT
                TypeError.SERVER_RESPONSE_ERROR.ordinal -> TypeError.SERVER_RESPONSE_ERROR
                else -> TypeError.DEFAULT
            }
    }
}

enum class TypeError {
    DEFAULT,
    SERVER_RESPONSE_ERROR
}