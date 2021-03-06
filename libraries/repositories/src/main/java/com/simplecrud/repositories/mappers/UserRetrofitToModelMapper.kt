package com.simplecrud.repositories.mappers

import com.simplecrud.repositories.models.UserDataModel
import com.simplecrud.api.models.UserModel

interface UserRetrofitToModelMapper {
    fun toMap(users: List<UserModel>): List<UserDataModel>
}

class UserRetrofitToModelMapperImp: UserRetrofitToModelMapper {
    override fun toMap(users: List<UserModel>): List<UserDataModel> =
        users.map { toMap(it) }

    private fun toMap(user: UserModel): UserDataModel =
        UserDataModel(
            id = user.id,
            name = user.name,
            birthdate = user.birthdate
        )
}