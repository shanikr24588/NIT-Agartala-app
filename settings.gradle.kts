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
        mavenCentral( )

        maven {
            url = uri("https://my.pspdfkit.com/maven")
        }

        maven {
            setUrl( "https://jitpack.io")
        }


    }
}

rootProject.name = "NIT-A"
include(":app")


 