package com.simplecrud.client

import android.app.Application
import com.simplecrud.adduser.di.addUserModule
import com.simplecrud.repositories.di.repositoriesModule
import com.simplecrud.api.di.apiModule
import com.simplecrud.client.di.routerModule
import com.simplecrud.listusers.di.listUsersModule
import com.simplecrud.removeuser.di.removeUserModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SimpleCrudApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@SimpleCrudApplication)
            modules(
                listOf(
                    addUserModule,
                    apiModule,
                    listUsersModule,
                    removeUserModule,
                    repositoriesModule,
                    routerModule
                )
            )
        }
    }
}