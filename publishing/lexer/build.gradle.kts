plugins {
    kotlin("multiplatform")
    `maven-publish`
    signing
}

kotlin {
    jvm()
    js {
        browser()
        nodejs()
    }
    wasmJs {
        browser()
        nodejs()
    }
    linuxX64()
    macosArm64()
    macosX64()

    sourceSets {
        commonMain {
            kotlin.srcDir("../../lexer/src")
            dependencies {
                api(project(":core"))
            }
        }
        commonTest {
            kotlin.srcDir("../../lexer/test")
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }

    targets.configureEach {
        mavenPublication {
            artifactId = "kss-lexer-${targetName.lowercase()}"
        }
    }
}

publishing {
    publications.withType<MavenPublication> {
        if (name == "kotlinMultiplatform") {
            artifactId = "kss-lexer"
        }
        pom {
            name.set("KSS Lexer")
            description.set("CSS tokenizer for KSS — Kotlin Style Sheets")
        }
    }
}
