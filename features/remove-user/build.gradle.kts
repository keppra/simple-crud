import com.simplecrud.gradle.dependencies.*

plugins {
    id("com.android.library")
    id("shared-properties")
    kotlin("android")
}

dependencies {
    implementation(project(":features:base"))
    implementation(project(":features:common-ui"))
    implementation(project(":libraries:repositories"))
    implementation(project(":libraries:utils"))
    implementation(UIDependencies.material)
    implementation(UIDependencies.constraintlayout)
}