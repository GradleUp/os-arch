package gradleUp

import org.gradle.api.Named
import org.gradle.api.attributes.Attribute

data class Platform(val os: OS, val arch: Arch) : Named {

    constructor(pair: Pair<OS, Arch>) : this(pair.first, pair.second)

    override fun getName() = "$os-$arch"

    override fun toString() = name

    val classifier = "natives-$name"

    companion object {
        val ATTRIBUTE = Attribute.of(Platform::class.java)
        val current: Platform
            get() = Platform(OS.current, Arch.current)
    }
}