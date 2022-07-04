package com.simplecrud.repositories

import com.simplecrud.repositories.models.UserDataModel
import com.simplecrud.repositories.repos.UsersRepository
import io.reactivex.rxjava3.core.Single

class RepositoryImp(
    private val repository: UsersRepository
): Repository {
    override fun getUsers(): Single<List<UserDataModel>> =
        repository.getUsers()

    override fun adUser(user: UserDataModel): Single<UserDataModel> =
        repository.addUser(user)
}