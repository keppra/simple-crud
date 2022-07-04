package com.simplecrud.gradle.extensions

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import com.simplecrud.gradle.dependencies.*

fun Project.addsKoinDependencies() {
    dependencies {
        add(DependenciesType.IMPLEMENTATION.toString(), CoreDependencies.koin_android)
        add(DependenciesType.TESTING.toString(), TestingDependencies.koin)
        add(DependenciesType.ANDROID_TESTING.toString(), TestingDependencies.koin_junit)
    }
}

fun Project.addsRoomDependencies() {
    dependencies {
        add(DependenciesType.IMPLEMENTATION.toString(), CoreDependencies.room_runtime)
        add(DependenciesType.IMPLEMENTATION.toString(), CoreDependencies.room_rxjava3)
        add(DependenciesType.KAPT.toString(), AnnotationProcessorDependencies.room)
    }
}

fun Project.addsRxDependencies() {
    dependencies {
        add(DependenciesType.IMPLEMENTATION.toString(), CoreDependencies.rxAndroid)
        add(DependenciesType.IMPLEMENTATION.toString(), CoreDependencies.rxJava)
        add(DependenciesType.IMPLEMENTATION.toString(), CoreDependencies.rxKotlin)
    }
}

fun Project.addsRetrofitDependencies() {
    dependencies {
        add(DependenciesType.IMPLEMENTATION.toString(), CoreDependencies.retrofit)
        add(DependenciesType.IMPLEMENTATION.toString(), CoreDependencies.retrofit_adapter_rxjava)
        add(DependenciesType.IMPLEMENTATION.toString(), CoreDependencies.retrofit_converter_gson)
    }
}

fun Project.addsOkhttpDependencies() {
    dependencies {
        add(DependenciesType.IMPLEMENTATION.toString(), CoreDependencies.okhttp)
        add(DependenciesType.IMPLEMENTATION.toString(), CoreDependencies.okhttp_logging_interceptor)
    }
}