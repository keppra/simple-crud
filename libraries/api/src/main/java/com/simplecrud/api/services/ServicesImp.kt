package com.simplecrud.api.services

import com.simplecrud.api.models.UserModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class ServicesImp(
    private val apiServices: ApiServices
): Services {
    override fun getUsers(): Single<List<UserModel>> =
        apiServices.getUsers()

    override fun addUser(userModel: UserModel): Completable =
        apiServices.addUser(userModel)

    override fun removeUser(id: Int?): Completable =
        apiServices.removeUser(id = id)
}