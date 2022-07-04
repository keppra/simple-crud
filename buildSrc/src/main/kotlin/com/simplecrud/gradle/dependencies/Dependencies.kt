package com.simplecrud.gradle.dependencies

object BuildPlugins {
    val kotlin_gradle by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${DependenciesVersion.kotlin}" }
    val navigation_safe_args by lazy { "androidx.navigation:navigation-safe-args-gradle-plugin:${DependenciesVersion.nav_version}" }
}

object CoreDependencies {
    val appcompat = "androidx.appcompat:appcompat:${DependenciesVersion.appcompat}"
    val core_ktx by lazy { "androidx.core:core-ktx:${DependenciesVersion.core_ktx}" }
    val gradle by lazy { "com.android.tools.build:gradle:${DependenciesVersion.gradle}" }
    val koin_core by lazy { "io.insert-koin:koin-core:${DependenciesVersion.koin}" }
    val koin_android by lazy { "io.insert-koin:koin-android:${DependenciesVersion.koin}" }
    val navigation_fragment by lazy { "androidx.navigation:navigation-fragment-ktx:${DependenciesVersion.nav_version}" }
    val okhttp by lazy { "com.squareup.okhttp3:okhttp:${DependenciesVersion.okhttp}" }
    val okhttp_logging_interceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${DependenciesVersion.okhttp}" }
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${DependenciesVersion.retrofit}" }
    val retrofit_adapter_rxjava by lazy { "com.squareup.retrofit2:adapter-rxjava3:${DependenciesVersion.retrofit}" }
    val retrofit_converter_gson by lazy { "com.squareup.retrofit2:converter-gson:${DependenciesVersion.retrofit}" }
    val room_runtime by lazy { "androidx.room:room-runtime:${DependenciesVersion.room}" }
    val room_rxjava3 by lazy { "androidx.room:room-rxjava3:${DependenciesVersion.room}" }
    val rxAndroid by lazy { "io.reactivex.rxjava3:rxandroid:${DependenciesVersion.rxandroid}" }
    val rxJava by lazy { "io.reactivex.rxjava3:rxjava:${DependenciesVersion.rxjava}" }
    val rxKotlin by lazy { "io.reactivex.rxjava3:rxkotlin:${DependenciesVersion.rxkotlin}" }
}

object UIDependencies {
    val constraintlayout by lazy { "androidx.constraintlayout:constraintlayout:${DependenciesVersion.constraintlayout}" }
    val material by lazy { "com.google.android.material:material:${DependenciesVersion.material}" }
    val navigation_ui by lazy { "androidx.navigation:navigation-ui-ktx:${DependenciesVersion.nav_version}" }
}

object TestingDependencies {
    val junit by lazy { "junit:junit:${DependenciesVersion.junit}" }
    val koin by lazy { "io.insert-koin:koin-test:${DependenciesVersion.koin}" }
    val koin_junit by lazy { "io.insert-koin:koin-test-junit4${DependenciesVersion.koin}" }
}

object AndroidTestingDependencies {
    val junit by lazy { "androidx.test.ext:junit:${DependenciesVersion.android_junit}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${DependenciesVersion.espresso}" }
}

object AnnotationProcessorDependencies {
    val room by lazy { "androidx.room:room-compiler:${DependenciesVersion.room}" }
}

enum class DependenciesType {
    ANDROID_TESTING,
    ANNOTATION_PROCESSOR,
    IMPLEMENTATION,
    KAPT,
    TESTING;

    override fun toString(): String =
        when (this) {
            ANDROID_TESTING -> "androidTestImplementation"
            ANNOTATION_PROCESSOR -> "annotationProcessor"
            IMPLEMENTATION -> "implementation"
            KAPT -> "kapt"
            TESTING -> "testImplementation"
        }
}