plugins {
    id("io.micronaut.application")
    id("io.micronaut.test-resources")
    jacoco
    id("groovy")
}

dependencies {
    kapt("io.micronaut.data:micronaut-data-document-processor")

    implementation(project(":common"))
    implementation(project(":rest-contract"))
    implementation(project(":rabbit-contract"))

    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut:micronaut-http-server-netty")
    implementation("io.micronaut.rabbitmq:micronaut-rabbitmq")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.micronaut:micronaut-validation")
    implementation("io.micronaut.data:micronaut-data-mongodb")

    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("org.mongodb:mongodb-driver-sync")

    testImplementation("io.micronaut:micronaut-http-client")
}

micronaut {
    testRuntime("spock2")
    processing {
        incremental(true)
        annotations("dev.hypest.pis.*")
    }
    testResources {
        sharedServer.set(true)
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
    }
}

tasks.withType<JacocoReport> {
    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.map {
            fileTree(it).apply {
                exclude("dev/hypest/pis/**/*Test.class")
            }
        }))
    }
}
