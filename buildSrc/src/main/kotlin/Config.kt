object Config {
    val kotlinVersion = "1.4.0"

    object BuildPlugins {
        val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}"
        val androidGradlePlugin = "com.android.tools.build:gradle:4.0.1"
    }
    object Android {
        val minSdkVersion = 21
        val targetSdkVersion = 29
        val compileSdkVersion = 29
        val applicationId = "dev.anapsil.chucknorris"
        val versionCode = 1
        val versionName = "1.0"
    }
    object Libs {
        val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
        val androidCoreKtx = "androidx.core:core-ktx:1.3.1"
        val androidAppCompat = "androidx.appcompat:appcompat:1.2.0"
    }
    object TestLibs {
        val junit = "junit:junit:4.12"
        val androidJunitExtension = "androidx.test.ext:junit:1.1.1"
        val androidEspresso = "androidx.test.espresso:espresso-core:3.2.0"
    }
}