buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath(Config.BuildPlugins.androidGradlePlugin)
        classpath(Config.BuildPlugins.kotlinGradlePlugin)
    }
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjvm-default=compatibility")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}