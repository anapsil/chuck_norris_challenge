object Config {
    const val kotlinVersion = "1.4.0"
    const val detektPluginVersion = "1.12.0-RC1"

    object BuildPlugins {
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        const val androidGradlePlugin = "com.android.tools.build:gradle:4.0.1"
        const val detektPlugin = "io.gitlab.arturbosch.detekt"
    }

    object Android {
        const val minSdkVersion = 21
        const val targetSdkVersion = 29
        const val compileSdkVersion = 29
        const val applicationId = "dev.anapsil.chucknorris"
        const val versionCode = 1
        const val versionName = "1.0"
    }

    object Libs {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
        const val androidCoreKtx = "androidx.core:core-ktx:1.3.1"
        const val androidAppCompat = "androidx.appcompat:appcompat:1.2.0"
    }

    object TestLibs {
        const val junit = "junit:junit:4.12"
        const val androidJunitExtension = "androidx.test.ext:junit:1.1.1"
        const val androidEspresso = "androidx.test.espresso:espresso-core:3.2.0"
    }
}