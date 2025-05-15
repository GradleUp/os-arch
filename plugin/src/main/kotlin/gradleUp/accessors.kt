package gradleUp

import org.gradle.api.Named
import org.gradle.kotlin.dsl.named

inline fun <reified T : Named> org.gradle.api.model.ObjectFactory.named(os: OS): T = named(os.name)

inline fun <reified T : Named> org.gradle.api.model.ObjectFactory.named(arch: Arch): T = named(arch.name)