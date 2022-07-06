package com.simplecrud.repositories.repos

import com.simplecrud.repositories.datasources.UsersRemoteDataSource
import com.simplecrud.repositories.models.UserDataModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class UsersRepositoryImp(
    private val remoteSource: UsersRemoteDataSource
): UsersRepository {
    override fun getUsers(): Single<List<UserDataModel>> =
        remoteSource.getUsers()

    override fun addUser(user: UserDataModel): Completable =
        remoteSource.addUser(user)
}