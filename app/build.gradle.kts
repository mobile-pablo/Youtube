import java.io.FileInputStream
import java.util.Properties

apply(from = "../ktlint.gradle.kts")

plugins {
    libs.plugins.apply {
        listOf(
            com.android.application,
            org.jetbrains.kotlin.android,
            kotlinKapt,
            kotlinParcelize,
            gmsGoogle,
            hiltPlugin,
            kspPlugin,
            firebaseCrashlytics,
            kover
        ).map(::alias)
    }
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
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.mobile.pablo.youtube"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    /**
     * May not be possible for TV platform for now
     * Ticket: https://issuetracker.google.com/issues/232753501
     * If Doesn't work feel free to remove that part
     */
    testOptions {
        managedDevices {
            localDevices {
                create("pixel2api28").apply {
                    device = "Pixel 2"
                    apiLevel = 28
                    systemImageSource = "google-tv"
                }
            }
        }
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
        }
    }

    buildFeatures { compose = true }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.material.get()
    }

    kotlinOptions { jvmTarget = libs.versions.jvmTarget.get() }

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

    /**
     *    Android UI tests arent included in that report
     *    It misses like 60% of the tests I wrote. (Instrumented tests)
     *    Its not possible for Github Action to
     *    include coverage for UI test without a AWS Device Farm etc.
     *    Even plugin don't support that (currently).
     */
    koverReport {

        defaults {
            verify {
                rule("Multi-module project coverage should be at least 60%") {
                    minBound(60)
                }
            }
        }

        filters {
            excludes {
                classes(
                    "*BuildConfig*",
                    "*Destination*",
                    "*_Impl*",
                    "*Hilt_*",
                    "*Screen*",
                    "*_Hilt*",
                    "*MainActivity*",
                    "*_Factory*",
                    "*ComposableSingletons*",
                    "*Module_*",
                    "*Application*"
                )
                packages(
                    "dagger.hilt.*",
                    "*di*",
                    "*const*",
                    "hilt_aggregated_deps",
                    "*entity*",
                    "*uicomponents.theme*",
                    "*uicomponents.ext*",
                    "*view*",
                    "*model*",
                    "*mapper*",
                    "*wrapper*",
                    "*storage.sharedprefs*",
                    "*storage.database*"
                )
                annotatedBy("androidx.compose.runtime.Composable")
            }
        }
    }
}

tasks.getByPath("preBuild").dependsOn("ktlint")

dependencies {

    implementation(project(":uicomponents"))
    listOf("home", "search", "player", "error")
        .forEach { feature ->
            implementation(project(":feature:$feature"))
        }

    listOf(
        "core",
        "domain",
        "feature:home",
        "feature:search",
        "networking",
        "storage",
        "uicomponents"
    ).forEach {
        kover(project(":$it"))
    }

    libs.apply {
        bundles.apply {
            listOf(
                core.ktx,
                leanback,
                androidXBundle,
                composeBundle,
                tvBundle,
                hilt.android
            ).map(::implementation)

            kapt(hilt.compiler)
            ksp(compose.destination.ksp)

            debugImplementation(composeDebugBundle)
            testImplementation(testBundle)
            kaptAndroidTest(hilt.android.compiler)
            androidTestImplementation(androidTestBundle)
        }
    }
}
