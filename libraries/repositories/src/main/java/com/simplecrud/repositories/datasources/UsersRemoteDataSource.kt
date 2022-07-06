package com.simplecrud.repositories.datasources

import com.simplecrud.repositories.models.UserDataModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface UsersRemoteDataSource {
    fun getUsers(): Single<List<UserDataModel>>
    fun addUser(userDataModel: UserDataModel): Completable
    fun removeUser(userDataModel: UserDataModel): Completable
}