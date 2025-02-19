// build.gradle.kts (Project-level) ✅ REMOVE repositories
plugins {
    id("com.android.application") version "8.8.1" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
