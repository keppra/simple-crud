import com.simplecrud.gradle.dependencies.*

plugins {
    id("com.android.application")
    id("shared-properties")
    kotlin("android")
}

dependencies {
    implementation(project(":libraries:api"))
    implementation(project(":libraries:repositories"))
    implementation(UIDependencies.material)
    implementation(UIDependencies.constraintlayout)
    androidTestImplementation(AndroidTestingDependencies.espresso)
}