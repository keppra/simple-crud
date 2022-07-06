import com.simplecrud.gradle.dependencies.UIDependencies

plugins {
    id("com.android.library")
    id("shared-properties")
    kotlin("android")
}

dependencies {
    implementation(UIDependencies.constraintlayout)
    implementation(UIDependencies.material)
}