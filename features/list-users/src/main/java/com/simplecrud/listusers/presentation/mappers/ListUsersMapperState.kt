package com.simplecrud.listusers.presentation.mappers

import com.simplecrud.listusers.presentation.models.UsersPresentation
import com.simplecrud.listusers.presentation.viewstates.ErrorListUsersState
import com.simplecrud.listusers.presentation.viewstates.ListUsersState

class ListUsersMapperState {
    fun toMapList(
        state: ListUsersState,
        list: List<UsersPresentation>
    ): ListUsersState {
        state.list = list
        return state
    }

    fun toMapLoading(
        state: ListUsersState,
        isLoading: Boolean
    ): ListUsersState {
        state.isLoading = isLoading
        return state
    }

    fun toMapError(
        state: ListUsersState,
        errorMessage: String,
        code: Int
    ): ListUsersState {
        state.error = ErrorListUsersState(
            code = ErrorListUsersState.getTypeError(code),
            errorMessage = errorMessage
        )
        return state
    }
}