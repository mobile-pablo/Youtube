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
    namespace = "com.mobile.pablo.home"
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

    buildFeatures { compose = true }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.material.get()
    }

    kotlinOptions { jvmTarget = libs.versions.jvmTarget.get() }

    kapt { correctErrorTypes = true }

    ksp {
        arg(
            "compose-destinations.moduleName",
            "home"
        )
        arg(
            "compose-destinations.mode",
            "destinations"
        )
    }

    packaging {
        resources {
            excludes +=
                listOf(
                    "/META-INF/AL2.0",
                    "/META-INF/LGPL2.1",
                    "/META-INF/LICENSE.*",
                    "/META-INF/LICENSE-*.*"
                )
        }
    }
}

tasks.getByPath("preBuild").dependsOn("ktlint")

dependencies {
    listOf(
        "feature:player",
        "feature:error",
        "domain",
        "uicomponents"
    ).onEach { implementation(project(":$it")) }

    libs.apply {
        bundles.apply {
            listOf(
                composeBundle,
                tvBundle,
                paging.runtime,
                compose.paging,
                hilt.android
            ).map(::implementation)

            kapt(hilt.compiler)
            ksp(compose.destination.ksp)

            testImplementation(testBundle)
            androidTestImplementation(androidTestBundle)
        }
    }
}
