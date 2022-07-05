import com.simplecrud.gradle.dependencies.*

plugins {
    id("com.android.application")
    id("shared-properties")
    kotlin("android")
}

dependencies {
    implementation(project(":features:base"))
    implementation(project(":features:common-ui"))
    implementation(project(":libraries:api"))
    implementation(project(":libraries:repositories"))
    implementation(CoreDependencies.navigation_fragment)
    implementation(CoreDependencies.navigation_ui)
    implementation(UIDependencies.material)
    implementation(UIDependencies.constraintlayout)
    androidTestImplementation(AndroidTestingDependencies.espresso)
}