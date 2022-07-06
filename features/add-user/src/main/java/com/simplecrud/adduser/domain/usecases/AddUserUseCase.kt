package com.simplecrud.adduser.domain.usecases

import com.simplecrud.base.domain.extensions.dispatchers
import com.simplecrud.repositories.Repository
import com.simplecrud.repositories.models.UserDataModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class AddUserUseCase(
    private val repository: Repository
) {
    fun invokeWith(user: UserDataModel): Completable =
        repository.addUser(user)
            .dispatchers()
}