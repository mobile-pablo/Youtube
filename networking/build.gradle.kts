apply(from = "../ktlint.gradle.kts")

plugins {

    libs.plugins.apply {
        listOf(
            androidLibrary,
            org.jetbrains.kotlin.android,
            kotlinKapt,
            kotlinParcelize,
            firebaseCrashlytics,
            kover
        ).map(::alias)
    }
}

android {
    namespace = "com.mobile.pablo.networking"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        val serverUrl = "\"https://www.googleapis.com/\""
        debug {
            isMinifyEnabled = false
            buildConfigField(
                type = "String",
                name = "SERVER_URL",
                value = serverUrl
            )
        }

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField(
                type = "String",
                name = "SERVER_URL",
                value = serverUrl
            )
        }
    }

    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }

    kapt {
        correctErrorTypes = true
    }
}

tasks.getByPath("preBuild").dependsOn("ktlint")

dependencies {
    api(project(":storage"))

    libs.apply {
        bundles.apply {
            listOf(
                networkingBundle,
                hilt.android,
                paging.runtime
            ).map(::implementation)

            kapt(hilt.compiler)
            api(coroutine.core)

            testImplementation(testBundle)
            androidTestImplementation(androidTestBundle)
        }
    }
}
