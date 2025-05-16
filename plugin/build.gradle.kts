
plugins {
    // Apply the Java Gradle plugin development plugin to add support for developing Gradle plugins
    `java-gradle-plugin`

    // Apply the Kotlin JVM plugin to add support for Kotlin.
    embeddedKotlin("jvm")

    id("com.gradle.plugin-publish") version "1.2.1"

    `kotlin-dsl`
}

group = "io.github.gradleUp"
version = "0.0.5"

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

kotlin.jvmToolchain(8)

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use Kotlin Test test framework
            useKotlinTest(embeddedKotlinVersion)
        }
    }
}

gradlePlugin {
    website = "https://github.com/GradleUp/os-arch"
    vcsUrl = website
    // Define the plugin
    plugins.create("os-arch") {
        id = "io.github.gradleUp.os-arch"
        implementationClass = "gradleUp.OsArchPlugin"
        displayName = "os-arch"
        description = "Util Gradle plugin for retrieving os and arch of the underlying platform"
        tags = listOf("os", "operating-system", "operating", "system", "arch", "architecture", "attribute")
    }
}
