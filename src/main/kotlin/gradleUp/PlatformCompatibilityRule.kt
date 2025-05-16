package gradleUp

import org.gradle.api.attributes.AttributeCompatibilityRule
import org.gradle.api.attributes.CompatibilityCheckDetails

// Define the compatibility rule class
class PlatformCompatibilityRule : AttributeCompatibilityRule<Platform> {

    // Implement the execute method which will check compatibility
    override fun execute(details: CompatibilityCheckDetails<Platform>) {

        // Switch case to check the consumer value for supported Platform
        val consumer = details.consumerValue
        val producer = details.producerValue

        // according to the docs, they are never equal, so we can already discard that case
        if (consumer?.os == OS.osx && producer?.os == OS.osx)
            if (producer.arch == Arch.universal)
                if(consumer.arch == Arch.x86_64 || consumer.arch == Arch.aarch_64) {
                    details.compatible()
                    return
                }
        details.incompatible()
    }
}