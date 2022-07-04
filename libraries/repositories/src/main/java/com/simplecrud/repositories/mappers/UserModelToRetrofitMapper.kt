package com.simplecrud.repositories.mappers

import com.simplecrud.repositories.models.UserDataModel
import com.simplecrud.api.models.UserModel

interface UserModelToRetrofitMapper {
    fun toMap(user: UserDataModel): UserModel
}

class UserModelToRetrofitMapperImp: UserModelToRetrofitMapper {
    override fun toMap(user: UserDataModel): UserModel =
        UserModel(
            id = user.id,
            name = user.name,
            birthdate = user.birthdate
        )
}