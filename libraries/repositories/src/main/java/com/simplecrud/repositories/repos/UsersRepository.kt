package com.simplecrud.repositories.repos

import com.simplecrud.repositories.models.UserDataModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface UsersRepository {
    fun getUsers(): Single<List<UserDataModel>>
    fun addUser(user: UserDataModel): Completable
}