rootProject.name = "mineplex-gladiators-remake"

dependencyResolutionManagement {

    @Suppress("UnstableApiUsage")
    repositories {

        mavenLocal()
        mavenCentral()
        maven(uri("https://maven.leafmc.one/snapshots/"))
        maven(uri("https://repo.codemc.io/repository/maven-public/"))
        maven(uri("https://repo.papermc.io/repository/maven-public/"))
        maven(uri("https://libraries.minecraft.net/"))
        maven(uri("https://jitpack.io"))
        maven(uri("https://repo.tcoded.com/releases"))

    }

    versionCatalogs {

        create("build") {

            from(files("build.libs.toml"))

        }

    }

}