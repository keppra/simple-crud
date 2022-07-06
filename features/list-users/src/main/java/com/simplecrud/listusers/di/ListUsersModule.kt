package com.simplecrud.listusers.di

import com.simplecrud.listusers.domain.usecases.GetUsersUseCase
import com.simplecrud.listusers.presentation.mappers.ListUsersMapperPresentation
import com.simplecrud.listusers.presentation.mappers.ListUsersMapperRenderables
import com.simplecrud.listusers.presentation.mappers.ListUsersMapperState
import com.simplecrud.listusers.presentation.viewmodels.ListUsersViewModel
import com.simplecrud.listusers.presentation.views.ListUsersFragment
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listUsersModule = module {
    single { GetUsersUseCase(get()) }
    single { ListUsersMapperPresentation(androidContext()) }
    single { ListUsersMapperState() }
    single { ListUsersMapperRenderables(androidContext()) }
    viewModel { ListUsersViewModel(get(), get(), get()) }

    scope<ListUsersFragment> {
        scoped {}
    }
}