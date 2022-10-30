plugins {
    id("io.micronaut.application")
    id("maven-publish")
}

dependencies {
    implementation(project(":common"))
    implementation("io.micronaut:micronaut-http-server-netty")
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/PIS22Z/backend")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifact(sourcesJar)
        }
    }
}
