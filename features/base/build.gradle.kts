plugins {
    id("com.android.library")
    id("shared-properties")
    kotlin("android")
}

dependencies {
    implementation(project(":features:common-ui"))
    implementation(project(":libraries:k-progress-hud"))
}