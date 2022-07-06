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
    implementation(project(":libraries:recyclerview-renders"))
    implementation(project(":libraries:utils"))
    implementation(UIDependencies.constraintlayout)
    implementation(UIDependencies.material)
    implementation(UIDependencies.recyclerView)
    implementation(UIDependencies.swipeRefreshLayout)
}