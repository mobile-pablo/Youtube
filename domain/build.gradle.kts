apply(from = "../ktlint.gradle.kts")

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.firebaseCrashlytics)
    alias(libs.plugins.kover)
}

android {
    namespace = "com.mobile.pablo.domain"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

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

    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }

    kapt {
        correctErrorTypes = true
    }

    packaging {
        resources {
            excludes += "/META-INF/AL2.0"
            excludes += "/META-INF/LGPL2.1"
            excludes += "/META-INF/LICENSE.*"
            excludes += "/META-INF/LICENSE-*.*"
        }
    }
}

tasks.getByPath("preBuild").dependsOn("ktlint")

dependencies {
    api(project(":networking"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.paging.runtime)
    implementation(libs.compose.paging)

    testImplementation(libs.bundles.testBundle)
    androidTestImplementation(libs.bundles.androidTestBundle)
}
