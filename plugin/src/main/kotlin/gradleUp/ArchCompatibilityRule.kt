package gradleUp

import org.gradle.api.attributes.AttributeCompatibilityRule
import org.gradle.api.attributes.CompatibilityCheckDetails

// Define the compatibility rule class
class ArchCompatibilityRule : AttributeCompatibilityRule<Arch> {
    // Implement the execute method which will check compatibility
    override fun execute(details: CompatibilityCheckDetails<Arch>) {
        // Switch case to check the consumer value for supported Architectures
        val currentArch = Arch.current
        when {
            OS.current == OS.osx && details.consumerValue == Arch.universal &&
            (currentArch == Arch.x86_64 || currentArch == Arch . aarch_64) -> details.compatible()
            else -> details.incompatible()
        }
    }
}