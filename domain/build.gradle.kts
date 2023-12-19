apply(from = "../ktlint.gradle.kts")

plugins {
    libs.plugins.apply {
        listOf(
            androidLibrary,
            kotlinKapt,
            org.jetbrains.kotlin.android,
            firebaseCrashlytics,
            kover
        ).map(::alias)
    }
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

    kapt { correctErrorTypes = true }

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
    api(project(":networking"))

    libs.apply {
        listOf(
            hilt.android,
            paging.runtime,
            compose.paging
        ).map(::implementation)

        kapt(hilt.compiler)

        testImplementation(bundles.testBundle)
        androidTestImplementation(bundles.androidTestBundle)
    }
}
