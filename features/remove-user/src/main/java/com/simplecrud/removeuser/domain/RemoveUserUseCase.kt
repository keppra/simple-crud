package com.simplecrud.removeuser.domain

import com.simplecrud.base.domain.extensions.dispatchers
import com.simplecrud.repositories.Repository
import com.simplecrud.repositories.models.UserDataModel
import io.reactivex.rxjava3.core.Completable

class RemoveUserUseCase(
    private val repository: Repository
) {
    fun invokeWith(user: UserDataModel): Completable =
        repository.removeUser(user)
            .dispatchers()
}