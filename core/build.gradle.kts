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
    namespace = "com.mobile.pablo.core"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    kotlinOptions { jvmTarget = libs.versions.jvmTarget.get() }

    kapt { correctErrorTypes = true }
}

tasks.getByPath("preBuild").dependsOn("ktlint")

dependencies {
    libs.apply {
        bundles.apply {
            listOf(
                androidXBundle,
                hilt.android,
                firebase.analytics,
                firebase.crashlytics
            ).map(::implementation)

            kapt(hilt.compiler)
            api(moshiBundle)
            api(timber)

            testImplementation(testBundle)
            androidTestImplementation(androidTestBundle)
        }
    }
}
