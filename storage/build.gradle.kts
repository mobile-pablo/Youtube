apply(from = "../ktlint.gradle.kts")

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.kspPlugin)
    alias(libs.plugins.firebaseCrashlytics)
}

android {
    namespace = "com.mobile.pablo.storage"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
                arguments["room.incremental"] = "true"
                arguments["room.expandProjection"] = "true"
            }
        }
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
        jvmTarget = "1.8"
    }

    kapt {
        correctErrorTypes = true
    }

    packaging {
        resources {
            excludes += "META-INF/*"
        }
    }
}

tasks.getByPath("preBuild").dependsOn("ktlint")

dependencies {
    api(project(":core"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.paging.runtime)

    api(libs.room.ktx)
    ksp(libs.room.compiler)
    implementation(libs.androidx.security)

    testImplementation(libs.bundles.testBundle)
    androidTestImplementation(libs.bundles.androidTestBundle)
}
