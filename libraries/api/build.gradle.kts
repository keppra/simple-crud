import com.simplecrud.gradle.extensions.addsOkhttpDependencies
import com.simplecrud.gradle.extensions.addsRetrofitDependencies

plugins {
    id("com.android.library")
    id("shared-properties")
    kotlin("android")
}

android {
    buildTypes {
        getByName("release") {
            buildConfigField("String", "BASE_URL_USERS", com.simplecrud.gradle.dependencies.AppConfig.baseUrlHelloWorldApi)
        }
        getByName("debug") {
            buildConfigField("String", "BASE_URL_USERS", com.simplecrud.gradle.dependencies.AppConfig.baseUrlHelloWorldApi)
        }
    }
}

addsOkhttpDependencies()
addsRetrofitDependencies()