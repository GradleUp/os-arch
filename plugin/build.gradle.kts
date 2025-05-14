
plugins {
    // Apply the Java Gradle plugin development plugin to add support for developing Gradle plugins
    `java-gradle-plugin`

    // Apply the Kotlin JVM plugin to add support for Kotlin.
    embeddedKotlin("jvm")
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

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
    // Define the plugin
    plugins.create("os-arch") {
        id = "gradleUp.os-arch"
        implementationClass = "gradleUp.OsArchPlugin"
    }
}
