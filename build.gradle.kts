import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    id("io.github.goooler.shadow") version "8.1.7"
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    mavenLocal()
}

val adventure = configurations.register("adventure")

configurations.compileClasspath.configure {
    extendsFrom(adventure.get())
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
    implementation("com.google.code.gson:gson:2.11.0")
    adventure("net.kyori:adventure-api:4.17.0")
    adventure("net.kyori:adventure-platform-bukkit:4.3.3")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    shadowJar {
        destinationDirectory = temporaryDir
        exclude("com.google.errorprone")
        relocate("com.google.gson", "de.dasbabypixel.adventuretesting.libs.gson")
    }
    jar {
        destinationDirectory = temporaryDir
    }
    val finalJar = register<ShadowJar>("finalJar") {
        from(shadowJar)
        dependsOn(adventure)
        configurations = listOf(adventure.get())
        relocate("net.kyori", "de.dasbabypixel.adventuretesting.libs.kyori")
    }
    assemble {
        dependsOn(finalJar)
    }
}