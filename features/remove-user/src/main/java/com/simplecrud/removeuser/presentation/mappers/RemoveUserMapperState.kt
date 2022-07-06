package com.simplecrud.removeuser.presentation.mappers

import com.simplecrud.removeuser.presentation.viewstates.ErrorRemoveUserState
import com.simplecrud.removeuser.presentation.viewstates.RemoveUserState

class RemoveUserMapperState {
    fun toMapField(
        state: RemoveUserState,
        id: Int
    ): RemoveUserState {
        state.id = id
        return state
    }

    fun toMapLoading(
        state: RemoveUserState,
        isLoading: Boolean
    ): RemoveUserState {
        state.isLoading = isLoading
        return state
    }

    fun toMapError(
        state: RemoveUserState,
        errorMessage: String,
        code: Int
    ): RemoveUserState {
        state.error = ErrorRemoveUserState(
            code = ErrorRemoveUserState.getTypeError(code),
            errorMessage = errorMessage
        )
        return state
    }
}