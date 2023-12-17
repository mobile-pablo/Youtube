apply(from = "../ktlint.gradle.kts")

plugins {
    listOf(
        libs.plugins.androidLibrary,
        libs.plugins.org.jetbrains.kotlin.android,
        libs.plugins.kotlinKapt,
        libs.plugins.kotlinParcelize,
        libs.plugins.hiltPlugin,
        libs.plugins.kspPlugin,
        libs.plugins.firebaseCrashlytics,
        libs.plugins.kover
    ).map(::alias)
}

android {
    namespace = "com.mobile.pablo.uicomponents"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures { compose = true }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.material.get()
    }

    kotlinOptions { jvmTarget = libs.versions.jvmTarget.get() }

    kapt { correctErrorTypes = true }

    hilt { enableAggregatingTask = true }

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
    api(project(":core"))

    libs.apply {
        bundles.apply {
            listOf(
                core.ktx,
                leanback,
                androidXBundle,
                composeBundle,
                hilt.android
            ).map(::implementation)

            kapt(hilt.compiler)
            ksp(compose.destination.ksp)

            testImplementation(testBundle)
            debugImplementation(composeDebugBundle)
            kaptAndroidTest(hilt.android.compiler)
            androidTestImplementation(androidTestBundle)
        }
    }
}
