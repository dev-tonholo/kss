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
            kotlin.srcDir("../../parser/src")
            dependencies {
                api(project(":core"))
                api(project(":lexer"))
            }
        }
        commonTest {
            kotlin.srcDir("../../parser/test")
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }

    targets.configureEach {
        mavenPublication {
            artifactId = "kss-parser-${targetName.lowercase()}"
        }
    }
}

publishing {
    publications.withType<MavenPublication> {
        if (name == "kotlinMultiplatform") {
            artifactId = "kss-parser"
        }
        pom {
            name.set("KSS Parser")
            description.set("CSS parser for KSS — Kotlin Style Sheets")
        }
    }
}
