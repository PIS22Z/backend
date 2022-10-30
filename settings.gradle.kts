pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    val kotlinPluginVersion: String by settings
    val shadowJarPluginVersion: String by settings
    val micronautPluginVersion: String by settings
    val springDependencyManagementPluginVersion: String by settings
    plugins {
        id("org.jetbrains.kotlin.jvm") version kotlinPluginVersion
        id("org.jetbrains.kotlin.kapt") version kotlinPluginVersion
        id("org.jetbrains.kotlin.plugin.allopen") version kotlinPluginVersion
        id("com.github.johnrengelman.shadow") version shadowJarPluginVersion
        id("io.micronaut.application") version micronautPluginVersion
        id("io.micronaut.test-resources") version micronautPluginVersion
        id("io.spring.dependency-management") version springDependencyManagementPluginVersion
    }
}

rootProject.name = "backend"
include(":common")
include(":rest-contract")
include(":rabbit-contract")
include(":application")
