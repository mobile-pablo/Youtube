apply(from = "../ktlint.gradle.kts")

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.kotlinParcelize)
}

android {
    namespace = "com.mobile.pablo.networking"
    compileSdk = 33

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false

            //TODO: temp address
            buildConfigField(
                type = "String",
                name = "SERVER_URL",
                value = "\"https://localhost:8080/\""
            )
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            //TODO: temp address
            buildConfigField(
                type = "String",
                name = "SERVER_URL",
                value = "\"https://localhost:8080/\""
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
    api(project(":storage"))

    implementation(libs.bundles.networkingBundle)

    api(libs.coroutine.core)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidTestBundle)
}