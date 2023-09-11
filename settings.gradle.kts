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

rootProject.name = "Youtube"
include(":app")
include(":core")
include(":domain")
include(":networking")
include(":storage")
include(":uicomponents")
include(":feature:home")
