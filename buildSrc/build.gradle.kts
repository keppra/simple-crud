plugins{
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("shared-properties") {
            id = "shared-properties"
            implementationClass = "com.simplecrud.gradle.plugins.SharedPropertiesPlugin"
        }
        register("jacoco-plugin") {
            id = "jacoco-plugin"
            implementationClass = "com.simplecrud.gradle.plugins.JacocoPlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    compileOnly(gradleApi())
    implementation("com.android.tools.build:gradle:7.1.3")
    implementation(kotlin("gradle-plugin", "1.6.21"))
    implementation(kotlin("android-extensions"))
}