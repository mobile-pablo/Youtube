@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Purple Video Player"
include(
    ":app",
    ":core",
    ":domain",
    ":networking",
    ":storage",
    ":uicomponents",
    ":playground",
    ":feature:home",
    ":feature:search",
    ":feature:player",
    ":feature:error"
)
