import java.io.FileInputStream
import java.util.Properties

apply(from = "../ktlint.gradle.kts")

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.gmsGoogle)
    alias(libs.plugins.hiltPlugin)
    alias(libs.plugins.kspPlugin)
    alias(libs.plugins.firebaseCrashlytics)
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

val keystorePropertiesFile = rootProject.file("key.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    namespace = "com.mobile.pablo.youtube"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.mobile.pablo.youtube"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
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
        kotlinCompilerExtensionVersion = "1.5.3"
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
    implementation(project(":feature:home"))
    implementation(project(":feature:error"))
    implementation(project(":uicomponents"))

    implementation(libs.core.ktx)
    implementation(libs.leanback)

    implementation(libs.bundles.androidXBundle)
    implementation(libs.bundles.composeBundle)
    implementation(libs.bundles.tvBundle)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    kaptAndroidTest(libs.hilt.android.compiler)
    ksp(libs.compose.destination.ksp)

    testImplementation(libs.junit)
    debugImplementation(libs.bundles.composeDebugBundle)
    androidTestImplementation(libs.bundles.androidTestBundle)
}