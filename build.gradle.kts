// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.kotlinKapt) apply false
    alias(libs.plugins.kotlinParcelize) apply false
    alias(libs.plugins.gmsGoogle) apply false
    alias(libs.plugins.hiltPlugin) apply false
    alias(libs.plugins.kspPlugin) apply false
    alias(libs.plugins.androidLibrary) apply false
}
true // Needed to make the Suppress annotation work for the plugins block

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
    delete(rootProject.buildDir)
}
