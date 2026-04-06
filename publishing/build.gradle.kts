plugins {
    kotlin("multiplatform") version "2.3.20" apply false
}

val kssGroup = "dev.tonholo.kss"
val kssVersion = "1.0.0"

subprojects {
    group = kssGroup
    version = kssVersion
}

// Shared POM and publishing configuration for all subprojects that apply maven-publish.
subprojects {
    afterEvaluate {
        extensions.findByType<PublishingExtension>()?.apply {
            repositories {
                maven {
                    name = "mavenCentral"
                    url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
                    credentials {
                        username = findProperty("sonatype.username") as String? ?: System.getenv("SONATYPE_USERNAME")
                        password = findProperty("sonatype.password") as String? ?: System.getenv("SONATYPE_PASSWORD")
                    }
                }
                maven {
                    name = "githubPackages"
                    url = uri("https://maven.pkg.github.com/rafaeltonholo/kss")
                    credentials {
                        username = findProperty("github.username") as String? ?: System.getenv("GITHUB_ACTOR")
                        password = findProperty("github.token") as String? ?: System.getenv("GITHUB_TOKEN")
                    }
                }
            }

            publications.withType<MavenPublication> {
                pom {
                    url.set("https://github.com/rafaeltonholo/kss")
                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }
                    developers {
                        developer {
                            id.set("rafaeltonholo")
                            name.set("Rafael Tonholo")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://github.com/rafaeltonholo/kss.git")
                        developerConnection.set("scm:git:ssh://github.com:rafaeltonholo/kss.git")
                        url.set("https://github.com/rafaeltonholo/kss")
                    }
                }
            }
        }

        extensions.findByType<SigningExtension>()?.apply {
            val signingKey = findProperty("signing.key") as String? ?: System.getenv("GPG_PRIVATE_KEY")
            val signingPassword = findProperty("signing.password") as String? ?: System.getenv("GPG_PASSPHRASE")
            if (signingKey != null) {
                useInMemoryPgpKeys(signingKey, signingPassword)
                sign(extensions.getByType<PublishingExtension>().publications)
            }
        }
    }
}
