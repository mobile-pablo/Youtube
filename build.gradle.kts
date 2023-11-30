// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.kotlinKapt) apply false
    alias(libs.plugins.kotlinParcelize) apply false
    alias(libs.plugins.gmsGoogle) apply false
    alias(libs.plugins.hiltPlugin) apply false
    alias(libs.plugins.kspPlugin) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.firebaseCrashlytics) apply false
    alias(libs.plugins.benmanesVersions) apply true
    alias(libs.plugins.versionCatalogUpdate) apply true
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "<https://jitpack.io>")
    }
    dependencies {
        classpath(libs.buildGradlePlugin)
        classpath(libs.kotlinGradlePlugin)
        classpath(libs.hiltPlugin)
        classpath(libs.googleServicesPlugin)
    }
}

tasks.register(
    "clean",
    Delete::class
) {
    delete(rootProject.layout.buildDirectory)
}
