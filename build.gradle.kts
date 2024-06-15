plugins {
    java
    id("io.github.goooler.shadow") version "8.1.7"
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    mavenLocal()
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
    implementation("net.kyori:adventure-api:4.17.0")
    implementation("net.kyori:adventure-platform-bukkit:4.3.3")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    shadowJar {
        archiveClassifier = null
        relocate("net.kyori", "de.dasbabypixel.adventuretesting.libs.kyori")
    }
    jar {
        destinationDirectory = temporaryDir
    }
    assemble {
        dependsOn(shadowJar)
    }
}