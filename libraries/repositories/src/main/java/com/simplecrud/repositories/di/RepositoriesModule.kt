package com.simplecrud.repositories.di

import com.simplecrud.repositories.Repository
import com.simplecrud.repositories.RepositoryImp
import com.simplecrud.repositories.datasources.UsersRemoteDataSource
import com.simplecrud.repositories.datasources.UsersRemoteDataSourceImp
import com.simplecrud.repositories.mappers.UserModelToRetrofitMapper
import com.simplecrud.repositories.mappers.UserModelToRetrofitMapperImp
import com.simplecrud.repositories.mappers.UserRetrofitToModelMapper
import com.simplecrud.repositories.mappers.UserRetrofitToModelMapperImp
import com.simplecrud.repositories.repos.UsersRepository
import com.simplecrud.repositories.repos.UsersRepositoryImp
import org.koin.dsl.module

val repositoriesModule = module {
    single<Repository> { RepositoryImp(get()) }
    single<UsersRepository> { UsersRepositoryImp(get()) }
    single<UsersRemoteDataSource> { UsersRemoteDataSourceImp(get(), get()) }
    single<UserModelToRetrofitMapper> { UserModelToRetrofitMapperImp() }
    single<UserRetrofitToModelMapper> { UserRetrofitToModelMapperImp() }
}