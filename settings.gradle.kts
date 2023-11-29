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
include(":app")
include(":core")
include(":domain")
include(":networking")
include(":storage")
include(":uicomponents")
include(":feature:home")
include(":feature:search")
include(":feature:player")
include(":feature:error")
