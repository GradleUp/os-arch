package gradleUp

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.nativeplatform.MachineArchitecture
import org.gradle.nativeplatform.OperatingSystemFamily

class OsArchPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.configurations.configureEach {
            if (name.contains("runtime", ignoreCase = true)) {
                project.logger.info("setting attributes for '$name' configuration")
                attributes {
                    val platform = Platform.current
                    attribute(OperatingSystemFamily.OPERATING_SYSTEM_ATTRIBUTE, project.objects.named(platform.os))
                    attribute(MachineArchitecture.ARCHITECTURE_ATTRIBUTE, project.objects.named(platform.arch))
                }
            }
        }
    }
}
