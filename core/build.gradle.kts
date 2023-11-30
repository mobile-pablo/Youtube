apply(from = "../ktlint.gradle.kts")

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.firebaseCrashlytics)
}

android {
    namespace = "com.mobile.pablo.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    implementation(libs.bundles.androidXBundle)

    implementation(libs.hilt.android)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    kapt(libs.hilt.compiler)

    api(libs.bundles.moshiBundle)
    api(libs.timber)

    testImplementation(libs.bundles.testBundle)
    androidTestImplementation(libs.bundles.androidTestBundle)
}
