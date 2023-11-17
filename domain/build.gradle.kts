apply(from = "../ktlint.gradle.kts")

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.firebaseCrashlytics)
}

android {
    namespace = "com.mobile.pablo.domain"
    compileSdk = 33

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    kapt {
        correctErrorTypes = true
    }
}

tasks.getByPath("preBuild").dependsOn("ktlint")

dependencies {
    api(project(":networking"))

    testImplementation(libs.junit)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.paging.runtime)
    implementation(libs.compose.paging)

    testImplementation(libs.bundles.testBundle)
}