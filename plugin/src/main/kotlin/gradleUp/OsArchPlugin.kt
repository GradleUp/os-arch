package gradleUp

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.named

class OsArchPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.configurations["implementation"].attributes {
            attribute(Platform.ATTRIBUTE, project.objects.named(""))
        }
    }
}
