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
            kotlin.srcDir("../../core/src")
        }
    }

    targets.configureEach {
        mavenPublication {
            artifactId = "kss-core-${targetName.lowercase()}"
        }
    }
}

publishing {
    publications.withType<MavenPublication> {
        if (name == "kotlinMultiplatform") {
            artifactId = "kss-core"
        }
        pom {
            name.set("KSS Core")
            description.set("Core abstractions for KSS — Kotlin Style Sheets")
        }
    }
}
