package com.simplecrud.removeuser.di

import com.simplecrud.removeuser.domain.RemoveUserUseCase
import com.simplecrud.removeuser.presentation.mappers.RemoveUserMapperState
import com.simplecrud.removeuser.presentation.viewmodels.RemoveUserViewModel
import com.simplecrud.removeuser.presentation.views.RemoveUserFragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val removeUserModule = module {
    single { RemoveUserUseCase(get()) }
    single { RemoveUserMapperState() }
    viewModel { RemoveUserViewModel(get(), get()) }

    scope<RemoveUserFragment> {
        scoped {}
    }
}