package com.simplecrud.client.di

import androidx.appcompat.app.AppCompatActivity
import com.simplecrud.base.navigation.NavigationActivityStack
import com.simplecrud.base.navigation.NavigationActivityStackImp
import com.simplecrud.client.presentation.views.MainActivity
import com.simplecrud.client.router.Router
import org.koin.dsl.binds
import org.koin.dsl.module

val routerModule = module {
    scope<MainActivity> {
        scoped { (activity: AppCompatActivity) -> Router(activity, get()) }
    }

    single<NavigationActivityStack>{
        NavigationActivityStackImp()
    }
}