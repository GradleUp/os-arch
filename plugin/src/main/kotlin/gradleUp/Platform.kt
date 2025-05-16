package gradleUp

import org.gradle.api.Named
import org.gradle.api.Project
import org.gradle.api.attributes.*
import org.gradle.api.component.AdhocComponentWithVariants
import org.gradle.kotlin.dsl.named
import org.gradle.nativeplatform.MachineArchitecture
import org.gradle.nativeplatform.OperatingSystemFamily
import java.io.File

data class Platform(val os: OS, val arch: Arch) : Named {

    constructor(pair: Pair<OS, Arch>) : this(pair.first, pair.second)

    override fun getName() = "$os-$arch"

    override fun toString() = name

    val classifier = "natives-$name"

    fun Project.addVariant(native: File, notation: Any) {

        val nativeRuntimeElements = configurations.consumable("$classifier-RuntimeElements") {
            attributes {
                attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME)) // this is also by default
                attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.LIBRARY)) // this is also by default
                attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
                attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.JAR)) // this is also by default
                attribute(OperatingSystemFamily.OPERATING_SYSTEM_ATTRIBUTE, objects.named(os))
                attribute(MachineArchitecture.ARCHITECTURE_ATTRIBUTE, objects.named(arch))
            }
            outgoing {
                artifact(native) { classifier = this@Platform.classifier }
                if (notation is String) {
                    val ga = notation.substringBeforeLast(':')
                    val v = notation.substringAfterLast(':')
                    capability("$ga-$nativesCapability:$v")
                } else TODO("add other notation types (map and provider)")
            }
        }
        val javaComponent = components.findByName("java") as AdhocComponentWithVariants
        javaComponent.addVariantsFromConfiguration(nativeRuntimeElements.get()) {}
    }

    companion object {
        val ATTRIBUTE = Attribute.of(Platform::class.java)
        val nativesCapability = "natives"
        val current: Platform
            get() = Platform(OS.current, Arch.current)
    }
}