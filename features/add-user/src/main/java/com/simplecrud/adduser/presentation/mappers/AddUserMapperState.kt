package com.simplecrud.adduser.presentation.mappers

import com.simplecrud.adduser.presentation.viewstates.AddUserState
import com.simplecrud.adduser.presentation.viewstates.ErrorAddUserState
import com.simplecrud.adduser.presentation.viewstates.TypeField

class AddUserMapperState {
    fun toMapField(
        state: AddUserState,
        text: String,
        fieldType: Int
    ): AddUserState {
        when (fieldType) {
            TypeField.USERNAME.ordinal -> state.name = text
            TypeField.BIRTHDATE.ordinal -> state.birthdate = text
        }
        return state
    }

    fun toMapValidation(
        state: AddUserState,
        isNameValid: Boolean,
        isBirthdateValid: Boolean,
        isFormValid: Boolean
    ): AddUserState {
        state.isNameValid = isNameValid
        state.isBirthdateValid = isBirthdateValid
        state.isFormValid = isFormValid
        return state
    }

    fun toMapLoading(
        state: AddUserState,
        isLoading: Boolean
    ): AddUserState {
        state.isLoading = isLoading
        return state
    }

    fun toMapError(
        state: AddUserState,
        errorMessage: String,
        code: Int
    ): AddUserState {
        state.error = ErrorAddUserState(
            code = ErrorAddUserState.getTypeError(code),
            errorMessage = errorMessage
        )
        return state
    }
}