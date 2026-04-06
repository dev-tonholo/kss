plugins {
    `java-platform`
    `maven-publish`
    signing
}

javaPlatform {
    allowDependencies()
}

dependencies {
    constraints {
        api("dev.tonholo.kss:kss-core:${project.version}")
        api("dev.tonholo.kss:kss-lexer:${project.version}")
        api("dev.tonholo.kss:kss-parser:${project.version}")
    }
}

publishing {
    publications {
        create<MavenPublication>("bom") {
            from(components["javaPlatform"])
            artifactId = "kss-bom"
            pom {
                name.set("KSS BOM")
                description.set("Bill of Materials for KSS — Kotlin Style Sheets")
            }
        }
    }
}
