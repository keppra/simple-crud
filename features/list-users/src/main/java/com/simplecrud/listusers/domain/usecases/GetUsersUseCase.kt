package com.simplecrud.listusers.domain.usecases

import com.simplecrud.base.extensions.dispatchers
import com.simplecrud.repositories.Repository
import com.simplecrud.repositories.models.UserDataModel
import io.reactivex.rxjava3.core.Single

class GetUsersUseCase(
    private val repository: Repository
) {
    fun invoke(): Single<List<UserDataModel>> =
        repository.getUsers()
            .dispatchers()
}