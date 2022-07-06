import com.simplecrud.gradle.dependencies.*

plugins {
    id("com.android.application")
    id("shared-properties")
    kotlin("android")
}

dependencies {
    implementation(project(":features:add-user"))
    implementation(project(":features:base"))
    implementation(project(":features:common-ui"))
    implementation(project(":features:list-users"))
    implementation(project(":features:remove-user"))
    implementation(project(":features:summary"))
    implementation(project(":libraries:api"))
    implementation(project(":libraries:repositories"))
    implementation(CoreDependencies.navigation_fragment)
    implementation(CoreDependencies.navigation_ui)
    implementation(UIDependencies.material)
    implementation(UIDependencies.constraintlayout)
    androidTestImplementation(AndroidTestingDependencies.espresso)
}