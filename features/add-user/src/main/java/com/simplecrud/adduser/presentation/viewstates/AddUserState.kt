package com.simplecrud.adduser.presentation.viewstates

import com.simplecrud.commonui.state.ErrorState
import com.simplecrud.commonui.state.ErrorTypeState

data class AddUserState(
    var name: String = "",
    var birthdate: String = "",
    var isNameValid: Boolean = false,
    var isBirthdateValid: Boolean = false,
    var isFormValid: Boolean = false,
    var isLoading: Boolean = false,
    var error: ErrorAddUserState? = null
)

data class ErrorAddUserState(
    override val code: TypeError,
    override val errorMessage: String
): ErrorState<TypeError> {
    companion object: ErrorTypeState<TypeError> {
        override fun getTypeError(code: Int): TypeError =
            when (code) {
                TypeError.DEFAULT.ordinal -> TypeError.DEFAULT
                TypeError.FIELD_VALIDATION.ordinal -> TypeError.FIELD_VALIDATION
                TypeError.SERVER_RESPONSE_ERROR.ordinal -> TypeError.SERVER_RESPONSE_ERROR
                else -> TypeError.DEFAULT
            }
    }
}

enum class TypeError {
    DEFAULT,
    FIELD_VALIDATION,
    SERVER_RESPONSE_ERROR
}

enum class TypeField {
    USERNAME,
    BIRTHDATE
}