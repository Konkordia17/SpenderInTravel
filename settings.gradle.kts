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
include(":core_api")
include(":core")
include(":menu_impl")
include(":core_impl")
include(":menu_api")
include(":currency_converter_api")
include(":cost_accounting_impl")
include(":cost_accounting_api")
include(":activity")
include(":splash_api")
include(":splash_impl")
include(":history_impl")
include(":history_api")
