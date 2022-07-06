import com.simplecrud.gradle.dependencies.*

plugins {
    id("com.android.library")
    id("shared-properties")
    kotlin("android")
}

dependencies {
    implementation(project(":features:common-ui"))
    implementation(UIDependencies.material)
    implementation(UIDependencies.constraintlayout)
}