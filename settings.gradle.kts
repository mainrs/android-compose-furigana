pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    plugins {
        id("com.android.application") version("7.2.0")
        id("com.android.library") version("7.2.0")
        kotlin("android") version("1.6.10")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "android-compose-furigana"

include(":app")
include(":library")

enableFeaturePreview("VERSION_CATALOGS")
