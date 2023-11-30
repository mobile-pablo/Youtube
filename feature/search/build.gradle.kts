apply(from = "../../ktlint.gradle.kts")

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.firebaseCrashlytics)
    alias(libs.plugins.kspPlugin)
}

android {
    namespace = "com.mobile.pablo.search"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.material.get()
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    kapt {
        correctErrorTypes = true
    }

    ksp {
        arg(
            "compose-destinations.moduleName",
            "search"
        )
        arg(
            "compose-destinations.mode",
            "destinations"
        )
    }

    packaging {
        resources {
            excludes += "META-INF/*"
        }
    }
}

tasks.getByPath("preBuild").dependsOn("ktlint")

dependencies {
    implementation(project(":feature:error"))

    implementation(project(":domain"))
    implementation(project(":uicomponents"))

    implementation(libs.bundles.composeBundle)
    implementation(libs.bundles.tvBundle)
    ksp(libs.compose.destination.ksp)

    implementation(libs.paging.runtime)
    implementation(libs.compose.paging)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(libs.bundles.testBundle)
    androidTestImplementation(libs.bundles.androidTestBundle)
}
