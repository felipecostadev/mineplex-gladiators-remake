plugins {
    id("java")
    alias(build.plugins.shadowJar)

}

group = "com.mineplex"
version = "1.21.11-SNAPSHOT"

dependencies {
    implementation(build.libs.command)
    implementation(build.libs.inventory)
    implementation(build.libs.fastboard)
    implementation(build.libs.packets)

    compileOnly(build.libs.leaf)

    compileOnly(build.libs.fastutil)

    compileOnly(build.libs.lombok)
    annotationProcessor(build.libs.lombok)
}

tasks {

    shadowJar {

        relocate("dev.despical.commandframework", "com.mineplex.relocate.command")
        relocate("me.devnatan.inventoryframework", "com.mineplex.relocate.menu")
        relocate("com.tcoded.folialib", "com.mineplex.relocate.folialib")

        dependencies {
            exclude(dependency("com.github.pop4959:Chunky"))
        }

    }

}