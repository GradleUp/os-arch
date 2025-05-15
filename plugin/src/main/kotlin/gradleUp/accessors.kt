package gradleUp

import org.gradle.api.Named
import org.gradle.api.model.ObjectFactory
import org.gradle.kotlin.dsl.named
import java.io.File

inline fun <reified T : Named> ObjectFactory.named(os: OS): T = named(os.name)

inline fun <reified T : Named> ObjectFactory.named(arch: Arch): T = named(arch.name)

infix fun File.getOsArch(regex: Regex): Pair<OS, Arch> = regex.matchEntire(name)!!
        .destructured.let { (os, arch) -> OS.of(os) to Arch.of(arch) }

infix fun File.getPlatform(regex: Regex) = Platform(getOsArch(regex))

val defaultExtractor = Regex(".*-natives-([^-]+)-(.+?)\\.jar")

inline val File.osArch: Pair<OS, Arch>
    get() = getOsArch(defaultExtractor)

inline val File.platform: Platform
    get() = getPlatform(defaultExtractor)

