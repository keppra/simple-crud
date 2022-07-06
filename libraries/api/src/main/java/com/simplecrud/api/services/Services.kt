package com.simplecrud.api.services

import com.simplecrud.api.models.UserModel
import io.reactivex.rxjava3.core.Single

interface Services {
    fun getUsers(): Single<List<UserModel>>
    fun addUser(
        name: String?,
        birthdate: String?
    ): Single<UserModel>
}