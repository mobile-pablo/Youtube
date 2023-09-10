@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.kotlinParcelize)
   // alias(libs.plugins.gmsGoogle)
    alias(libs.plugins.hiltPlugin)
    alias(libs.plugins.kspPlugin)
}

android {
    namespace = "com.mobile.pablo.youtube"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.mobile.pablo.youtube"
        minSdk = 21
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.leanback)

    implementation(libs.hilt.android)
    annotationProcessor(libs.hilt.compiler)

    implementation(libs.bundles.composeBundle)
    implementation(libs.bundles.androidXBundle)
    implementation(libs.bundles.tvBundle)

    debugImplementation(libs.bundles.composeDebugBundle)

    androidTestImplementation(libs.bundles.androidTestBundle)
}