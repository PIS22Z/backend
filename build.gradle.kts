plugins {
    id("io.micronaut.application")
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.plugin.allopen")
    id("com.github.johnrengelman.shadow")
    id("io.spring.dependency-management")
    id("org.sonarqube") version "3.4.0.2513"
    id("io.gitlab.arturbosch.detekt") version "1.21.0"
}

group = "dev.hypest.pis"

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(from = "$rootDir/versions.gradle")

    repositories {
        mavenCentral()
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_11
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
        compileTestKotlin {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
}

if (hasProperty("buildScan")) {
    extensions.findByName("buildScan")?.withGroovyBuilder {
        setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
        setProperty("termsOfServiceAgree", "yes")
    }
}

detekt {
    source = files(
        "application/src/main/kotlin/",
        "application/src/test/kotlin/",
        "common/src/main/kotlin/",
        "rest-contract/src/main/kotlin/",
        "rabbit-contract/src/main/kotlin/"
    )
    config = files("detekt.yml")
    buildUponDefaultConfig = true // preconfigure defaults
    allRules = false // activate all available (even unstable) rules.
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true) // observe findings in your browser with structure and code snippets
        xml.required.set(true) // checkstyle like format mainly for integrations like Jenkins

    }
}

application {
    mainClass.set("dev.hypest.pis.ApplicationKt")
}

dependencies {
    implementation(project(":common"))
    implementation(project(":rest-contract"))
    implementation(project(":rabbit-contract"))
    implementation(project(":application"))
}
