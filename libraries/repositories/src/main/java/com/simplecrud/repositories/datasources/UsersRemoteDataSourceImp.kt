package com.simplecrud.repositories.datasources

import com.simplecrud.repositories.mappers.UserRetrofitToModelMapper
import com.simplecrud.repositories.models.UserDataModel
import com.simplecrud.api.services.Services
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class UsersRemoteDataSourceImp(
    private val remoteServices: Services,
    private val toModelMapper: UserRetrofitToModelMapper
): UsersRemoteDataSource {
    override fun getUsers(): Single<List<UserDataModel>> =
        remoteServices.getUsers()
            .map { toModelMapper.toMap(it) }

    override fun addUser(userDataModel: UserDataModel): Completable =
        remoteServices.addUser( // TODO (add mapper when DateTime was added)
            name = userDataModel.name,
            birthdate = userDataModel.birthdate
        )
}