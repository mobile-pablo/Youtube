import java.io.FileInputStream
import java.util.Properties

apply(from = "../ktlint.gradle.kts")

plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.gmsGoogle)
    alias(libs.plugins.hiltPlugin)
    alias(libs.plugins.kspPlugin)
    alias(libs.plugins.firebaseCrashlytics)
    alias(libs.plugins.kover)
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

    packaging {
        resources {
            excludes += "/META-INF/AL2.0"
            excludes += "/META-INF/LGPL2.1"
            excludes += "/META-INF/LICENSE.*"
            excludes += "/META-INF/LICENSE-*.*"
        }
    }

    koverReport {
        /*
              Android UI tests arent included in that report
               Here what is missing currently :
                - SearchDaoTest.kt
                 - SharedPreferencesManagerTest.kt
                - ErrorScreenTest.kt
                - HomeItemViewTest.kt

         */
        filters {
            excludes {
                classes(
                    "*BuildConfig*",
                    "*Destination*",
                    "*_Impl*",
                    "*Hilt_*",
                    "*Screen*",
                    "*View*",
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
    val features = listOf("home", "search", "player", "error")

    implementation(project(":uicomponents"))
    features.forEach { feature ->
        implementation(project(":feature:$feature"))
    }

    val modules =
        listOf(
            "core",
            "domain",
            "feature:home",
            "feature:search",
            "networking",
            "storage",
            "uicomponents"
        )

    modules.forEach {
        kover(project(":$it"))
    }

    implementation(libs.core.ktx)
    implementation(libs.leanback)

    implementation(libs.bundles.androidXBundle)
    implementation(libs.bundles.composeBundle)
    implementation(libs.bundles.tvBundle)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    kaptAndroidTest(libs.hilt.android.compiler)
    ksp(libs.compose.destination.ksp)

    debugImplementation(libs.bundles.composeDebugBundle)
    testImplementation(libs.bundles.testBundle)
    androidTestImplementation(libs.bundles.androidTestBundle)
}
