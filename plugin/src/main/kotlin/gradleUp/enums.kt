package gradleUp

// based on https://github.com/trustin/os-maven-plugin
// plus `android`, `armv6hf` and `universal`

enum class OS {
    aix,
    android,
    hpux,
    os400,
    linux,
    osx,
    freebsd,
    openbsd,
    netbsd,
    sunos,
    windows,
    zos;

    companion object {
        val current: OS
            get() = of(System.getProperty("os.name"))
        infix fun of(value: String): OS {
            val v = value.normalized()
            return when {
                v.startsWith(aix.name) -> aix
                v == android.name -> android
                v.startsWith(hpux.name) -> hpux
                // Avoid the names such as os4000
                v.startsWith(os400.name) && (v.length <= 5 || !v[5].isDigit()) -> os400
                v.startsWith(linux.name) -> linux
                v.startsWith("mac") || v.startsWith(osx.name) -> osx
                v.startsWith(freebsd.name) -> freebsd
                v.startsWith(openbsd.name) -> openbsd
                v.startsWith(netbsd.name) -> netbsd
                v.startsWith("solaris") || v.startsWith(sunos.name) -> sunos
                v.startsWith(windows.name) -> windows
                v.startsWith(zos.name) -> zos
                else -> error("invalid OS: $v ($value)")
            }
        }
    }
}

enum class Arch {
    x86_64,
    x86_32,
    itanium_64,
    itanium_32,
    sparc_32,
    sparc_64,
    arm_32,
    aarch_64,
    mips_32,
    mipsel_32,
    mips_64,
    mipsel_64,
    ppc_32,
    ppcle_32,
    ppc_64,
    ppcle_64,
    s390_32,
    s390_64,
    riscv,
    riscv64,
    e2k,
    loongarch_64,
    universal;

    companion object {
        val current: Arch
            get() = of(System.getProperty("os.arch"))
        infix fun of(value: String): Arch {
            val v = value.normalized()
            return when {
                v.matches(Regex("^(x8664|amd64|ia32e|em64t|x64)$")) -> x86_64
                v.matches(Regex("^(x8632|x86|i[3-6]86|ia32|x32)$")) -> x86_32
                v.matches(Regex("^(ia64w?|itanium64)$")) -> itanium_64
                v == "ia64n" -> itanium_32
                v.matches(Regex("^(sparc|sparc32)$")) -> sparc_32
                v.matches(Regex("^(sparcv9|sparc64)$")) -> sparc_64
                v.matches(Regex("^(arm|arm32)$")) || v == "armv6hf" -> arm_32
                v == "aarch64" -> aarch_64
                v.matches(Regex("^(mips|mips32)$")) -> mips_32
                v.matches(Regex("^(mipsel|mips32el)$")) -> mipsel_32
                v == "mips64" -> mips_64
                v == "mips64el" -> mipsel_64
                v.matches(Regex("^(ppc|ppc32)$")) -> ppc_32
                v.matches(Regex("^(ppcle|ppc32le)$")) -> ppcle_32
                v == "ppc64" -> ppc_64
                v == "ppc64le" -> ppcle_64
                v == "s390" -> s390_32
                v == "s390x" -> s390_64
                v.matches(Regex("^(riscv|riscv32)$")) -> riscv
                v == riscv64.name -> riscv64
                v == e2k.name -> e2k
                v == "loongarch64" -> loongarch_64
                v == universal.name -> universal
                else -> error("invalid Arch: $v ($value)")
            }
        }
    }
}

internal fun String.normalized() = lowercase().replace("[^a-z0-9]+".toRegex(), "")