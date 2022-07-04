import com.baseaplication.gradle.extensions.addsOkhttpDependencies
import com.baseaplication.gradle.extensions.addsRetrofitDependencies

plugins {
    id("com.android.library")
    id("shared-properties")
    kotlin("android")
}

android {
    buildTypes {
        getByName("release") {
            buildConfigField("String", "BASE_URL_BOOKS", com.baseaplication.gradle.dependencies.AppConfig.baseUrlApiGoogleBooks)
            buildConfigField("String", "KEY_API_GOOGLE_BOOKS", com.baseaplication.gradle.dependencies.AppConfig.keyApiGoogleBooks)
        }
        getByName("debug") {
            buildConfigField("String", "BASE_URL_BOOKS", com.baseaplication.gradle.dependencies.AppConfig.baseUrlApiGoogleBooks)
            buildConfigField("String", "KEY_API_GOOGLE_BOOKS", com.baseaplication.gradle.dependencies.AppConfig.keyApiGoogleBooks)
        }
    }
}

addsOkhttpDependencies()
addsRetrofitDependencies()