plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Config.Android.compileSdkVersion)

    defaultConfig {
        applicationId = Config.Android.applicationId
        minSdkVersion(Config.Android.minSdkVersion)
        targetSdkVersion(Config.Android.targetSdkVersion)
        versionCode = Config.Android.versionCode
        versionName = Config.Android.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            proguardFiles("proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
        }
    }

    sourceSets.configureEach {
        java.setSrcDirs(java.srcDirs + File("src/$name/kotlin"))
    }
}

dependencies {
    implementation(Config.Libs.kotlin)
    implementation(Config.Libs.androidCoreKtx)
    implementation(Config.Libs.androidAppCompat)
    testImplementation(Config.TestLibs.junit)
    androidTestImplementation(Config.TestLibs.androidJunitExtension)
    androidTestImplementation(Config.TestLibs.androidEspresso)
}