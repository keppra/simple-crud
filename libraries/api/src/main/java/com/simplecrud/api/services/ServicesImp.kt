package com.simplecrud.api.services

import com.simplecrud.api.models.UserModel
import io.reactivex.rxjava3.core.Single

class ServicesImp(
    private val apiServices: ApiServices
): Services {
    override fun getUsers(): Single<List<UserModel>> =
        apiServices.getUsers()

    override fun addUser(name: String, birthdate: String): Single<UserModel> =
        apiServices.addUser(name = name, birthdate = birthdate)
}