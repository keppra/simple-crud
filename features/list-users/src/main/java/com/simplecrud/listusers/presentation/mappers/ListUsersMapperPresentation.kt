package com.simplecrud.listusers.presentation.mappers

import android.content.Context
import com.simplecrud.listusers.presentation.models.*
import com.simplecrud.repositories.models.UserDataModel

class ListUsersMapperPresentation(private val context: Context) {
    fun toMap(
        users: List<UserDataModel>
    ): List<UsersPresentation> {
        val listUsers = users.map {
            ItemPresentation(
                name = it.name ?: context.getString(com.example.commonui.R.string.list_users_renderable_not_specified),
                birthdate = it.birthdate ?: context.getString(com.example.commonui.R.string.list_users_renderable_not_specified)
            )
        }
        val listPresentation: MutableList<UsersPresentation> = mutableListOf(
            DescriptionPresentation
        )
        listPresentation.addAll(listUsers)
        return listPresentation
    }
}