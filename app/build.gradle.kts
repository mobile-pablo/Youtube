apply(from = "../ktlint.gradle.kts")

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp") version "1.8.0-1.0.9"
}

kotlin {
    sourceSets {
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }

        getByName("release") {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}

android {
    namespace = "com.mobile_pablo.youtube_tv"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.mobile_pablo.youtube_tv"
        minSdk = 28
        targetSdk = 33

        versionCode = 1
        versionName = "1.0"

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
}

tasks.getByPath("preBuild").dependsOn("ktlint")

dependencies {
    implementation(project(":uicomponents"))
    implementation(project(":feature:home"))

    implementation(libs.androidX.core)
    implementation(libs.androidX.lifecycle)
    implementation(libs.leanback)

    implementation(libs.compose.activity)
    implementation(libs.compose.ui)
    implementation(libs.compose.material)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.google.truth) {
        exclude(group = "dagger.fastInit")
        exclude(group = "kapt.kotlin.generated")
        exclude(group = "org.checkerframework")
    }
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.espresso.barista.compose)
    androidTestImplementation(libs.espresso.barista) {
        exclude(group = "org.jetbrains.kotlin")
    }
    androidTestImplementation(libs.uiautomator)
    androidTestImplementation(libs.compose.junit)
    androidTestImplementation(libs.hilt.testing)
    androidTestImplementation(libs.compose.hiltNavigation)
    kaptAndroidTest(libs.hilt.android.compiler)

    debugImplementation(libs.compose.tooling)
    debugImplementation(libs.compose.testManifest)

    implementation(libs.compose.destination)
    ksp(libs.compose.destination.ksp)

    implementation(libs.baseline.profile)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
}
