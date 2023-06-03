plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("settings-plugin") {
            id = "settings-plugin"
            implementationClass = "SettingsPlugin"
        }
    }
}
