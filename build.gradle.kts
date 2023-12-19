// Top-level build file where you can add configuration options common to all sub-projects/modules.

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
            androidLibrary,
            firebaseCrashlytics,
            kover
        ).map(::alias).onEach { it.apply(false) }

        listOf(
            benmanesVersions,
            versionCatalogUpdate
        ).map(::alias).onEach { it.apply(true) }
    }
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "<https://jitpack.io>")
    }
    dependencies {
        libs.apply {
            listOf(
                buildGradlePlugin,
                kotlinGradlePlugin,
                hiltPlugin,
                googleServicesPlugin,
                koverPlugin
            ).onEach { classpath(it) }
        }
    }
}

tasks.register(
    "clean",
    Delete::class
) {
    delete(rootProject.layout.buildDirectory)
}
