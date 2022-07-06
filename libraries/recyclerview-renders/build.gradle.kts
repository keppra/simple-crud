import com.simplecrud.gradle.dependencies.*
import com.simplecrud.gradle.extensions.addsRxDependencies

plugins {
    id("com.android.library")
    id("shared-properties")
    kotlin("android")
}

addsRxDependencies()
dependencies {
    implementation(UIDependencies.recyclerView)
}
