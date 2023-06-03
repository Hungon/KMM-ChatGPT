import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

class SettingsPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")
            pluginManager.apply {
                hashMapOf<String, String>(
                    "org.jetbrains.kotlin.jvm" to libs.findVersion("kotlin").get().requiredVersion,
                    "org.jetbrains.kotlin.multiplatform" to libs.findVersion("kotlin").get().requiredVersion,
                    "org.jetbrains.kotlin.android" to libs.findVersion("kotlin").get().requiredVersion,
                    "org.jetbrains.compose" to libs.findVersion("agp").get().requiredVersion,
                    "com.android.application" to libs.findVersion("agp").get().requiredVersion,
                    "com.android.library" to libs.findVersion("compose").get().requiredVersion,
                ).forEach { name,version ->
                    apply("$name:$version")
                }
            }
        }
    }
}
