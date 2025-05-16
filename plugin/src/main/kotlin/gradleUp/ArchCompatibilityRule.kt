package gradleUp

import org.gradle.api.attributes.AttributeCompatibilityRule
import org.gradle.api.attributes.CompatibilityCheckDetails

// Define the compatibility rule class
class ArchCompatibilityRule : AttributeCompatibilityRule<Arch> {
    // Implement the execute method which will check compatibility
    override fun execute(details: CompatibilityCheckDetails<Arch>) {

        // Switch case to check the consumer value for supported Architectures
        val consumer = details.consumerValue
        val producer = details.producerValue

        // according to the docs, they are never equal, so we can already discard that case
        if (OS.current == OS.osx)
            if (producer == Arch.universal)
                if(consumer == Arch.x86_64 || consumer == Arch.aarch_64) {
                    details.compatible()
                    return
                }
        details.incompatible()
    }
}