package com.simplecrud.adduser.di

import com.simplecrud.adduser.domain.usecases.AddUserUseCase
import com.simplecrud.adduser.domain.usecases.AddUserValidateFieldsUseCase
import com.simplecrud.adduser.presentation.mappers.AddUserMapperState
import com.simplecrud.adduser.presentation.viewmodels.AddUserViewModel
import com.simplecrud.adduser.presentation.views.AddUserFragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addUserModule = module {
    single { AddUserUseCase(get()) }
    single { AddUserValidateFieldsUseCase() }
    single { AddUserMapperState() }
    viewModel { AddUserViewModel(get(), get(), get()) }

    scope<AddUserFragment> {
        scoped {}
    }
}