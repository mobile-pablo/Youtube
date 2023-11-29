apply(from = "../ktlint.gradle.kts")

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.hiltPlugin)
    alias(libs.plugins.kspPlugin)
    alias(libs.plugins.firebaseCrashlytics)
}

android {
    namespace = "com.mobile.pablo.uicomponents"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
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
    api(project(":core"))

    implementation(libs.core.ktx)
    implementation(libs.leanback)

    implementation(libs.bundles.androidXBundle)
    implementation(libs.bundles.composeBundle)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    kaptAndroidTest(libs.hilt.android.compiler)
    ksp(libs.compose.destination.ksp)

    testImplementation(libs.bundles.testBundle)
    debugImplementation(libs.bundles.composeDebugBundle)
    androidTestImplementation(libs.bundles.androidTestBundle)
}
