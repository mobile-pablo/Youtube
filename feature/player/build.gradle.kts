apply(from = "../../ktlint.gradle.kts")

plugins {
    libs.plugins.apply {
        listOf(
            androidLibrary,
            kotlinKapt,
            org.jetbrains.kotlin.android,
            firebaseCrashlytics,
            kspPlugin,
            kover
        ).map(::alias)
    }
}

android {
    namespace = "com.mobile.pablo.player"
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.material.get()
    }

    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }

    kapt {
        correctErrorTypes = true
    }

    ksp {
        arg(
            "compose-destinations.moduleName",
            "player"
        )
        arg(
            "compose-destinations.mode",
            "destinations"
        )
    }
}

tasks.getByPath("preBuild").dependsOn("ktlint")

dependencies {
    listOf(
        "domain",
        "uicomponents",
        "feature:error"
    ).forEach {
        implementation(project(":$it"))
    }

    libs.apply {
        bundles.apply {
            listOf(
                composeBundle,
                youtubeBundle,
                tvBundle,
                paging.runtime,
                compose.paging,
                hilt.android
            ).map(::implementation)
        }
    }

    kapt(libs.hilt.compiler)
    ksp(libs.compose.destination.ksp)

    testImplementation(libs.bundles.testBundle)
    androidTestImplementation(libs.bundles.androidTestBundle)
}
