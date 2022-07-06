package com.simplecrud.gradle.plugins

import com.simplecrud.gradle.dependencies.*
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.simplecrud.gradle.extensions.addsRxDependencies
import com.simplecrud.gradle.extensions.addsKoinDependencies
import com.simplecrud.gradle.extensions.addsRoomDependencies
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class SharedPropertiesPlugin: Plugin<Project> {
    override fun apply(project: Project) {

        // Plugins //

        project.plugins.apply("kotlin-android")
        project.plugins.apply("kotlin-android-extensions")
        project.plugins.apply("kotlin-kapt")
        project.plugins.apply("jacoco-plugin")

        // Android build common parameters //

        val androidExtension = project.extensions.getByName("android")
        if (androidExtension is BaseExtension) {
            androidExtension.apply {
                compileSdkVersion(AppConfig.compileSdkVersion)
                buildToolsVersion = AppConfig.buildToolsVersion
                defaultConfig {
                    targetSdk = AppConfig.targetSdk
                    minSdk = AppConfig.minSdk
                    versionCode = AppConfig.versionCode
                    versionName = AppConfig.versionName
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_11
                    targetCompatibility = JavaVersion.VERSION_11
                }
                project.tasks.withType(KotlinCompile::class.java).configureEach {
                    kotlinOptions {
                        jvmTarget = JavaVersion.VERSION_11.toString()
                    }
                }
                buildFeatures.viewBinding = true

                // Proguard rules //

                val proguardFile = "proguard-rules.pro"
                when (this) {
                    is LibraryExtension ->
                        defaultConfig {
                            consumerProguardFiles(proguardFile)
                        }
                    is AppExtension ->
                        buildTypes {
                            getByName("release") {
                                isMinifyEnabled = true
                                isShrinkResources = true
                                proguardFiles(
                                    getDefaultProguardFile("proguard-android-optimize.txt"),
                                    proguardFile
                                )
                            }
                            getByName("debug") {

                            }
                    }
                }
            }
        }

        // Dependencies //

        project.addsRxDependencies()
        project.addsKoinDependencies()
        project.addsRoomDependencies()
        project.dependencies {
            add(DependenciesType.IMPLEMENTATION.toString(), CoreDependencies.core_ktx)
            add(DependenciesType.IMPLEMENTATION.toString(), CoreDependencies.appcompat)
            add(DependenciesType.ANDROID_TESTING.toString(), AndroidTestingDependencies.junit)
            add(DependenciesType.ANDROID_TESTING.toString(), AndroidTestingDependencies.espresso)
        }
    }
}