package com.simplecrud.repositories

import com.simplecrud.repositories.models.UserDataModel
import com.simplecrud.repositories.repos.UsersRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RepositoryImp(
    private val repository: UsersRepository
): Repository {
    override fun getUsers(): Single<List<UserDataModel>> =
        repository.getUsers()

    override fun addUser(user: UserDataModel): Completable =
        repository.addUser(user)

    override fun removeUser(user: UserDataModel): Completable =
        repository.removeUser(user)
}