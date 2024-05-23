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

rootProject.name = "SpenderInTravel"
include(":app")
include(":currency_converter_impl")
include(":network_impl")
include(":network_api")
include(":network")
include(":core_api")
include(":core")
include(":main")
include(":core_impl")
include(":main_api")
include(":currency_converter_api")
include(":currency_converter")
