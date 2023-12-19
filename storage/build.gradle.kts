apply(from = "../ktlint.gradle.kts")

plugins {
    libs.plugins.apply {
        listOf(
            androidLibrary,
            kotlinKapt,
            org.jetbrains.kotlin.android,
            kspPlugin,
            firebaseCrashlytics,
            kover
        ).map(::alias)
    }
}

android {
    namespace = "com.mobile.pablo.storage"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
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
        jvmTarget = libs.versions.jvmTarget.get()
    }

    kapt {
        correctErrorTypes = true
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
    api(project(":core"))

    libs.apply {
        listOf(
            hilt.android,
            paging.runtime,
            androidx.security
        ).map(::implementation)

        kapt(hilt.compiler)

        api(room.ktx)
        ksp(room.compiler)

        testImplementation(bundles.testBundle)
        androidTestImplementation(bundles.androidTestBundle)
    }
}
