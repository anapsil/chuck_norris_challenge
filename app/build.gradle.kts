plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("kotlin-android")
}

apply {
    from("$rootDir/config/detekt/detekt.gradle")
}

android {
    compileSdkVersion(Config.Android.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Config.Android.minSdkVersion)
        targetSdkVersion(Config.Android.targetSdkVersion)
        applicationId = Config.Android.applicationId
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

    buildFeatures {
        viewBinding = true
    }

    sourceSets.configureEach {
        java.setSrcDirs(java.srcDirs + File("src/$name/kotlin"))
    }
}

dependencies {
    implementation(Config.Libs.kotlin)
    implementation(Config.Libs.androidCoreKtx)
    implementation(Config.Libs.androidAppCompat)
    implementation(Config.Libs.androidCardView)
    implementation(Config.Libs.androidConstraintLayout)
    implementation(Config.Libs.androidRecyclerView)
    implementation(Config.Libs.androidMaterialDesign)
    implementation(Config.Libs.jetpackViewModel)
    implementation(Config.Libs.jetpackLiveData)
    implementation(Config.Libs.koinAndroid)
    implementation(Config.Libs.koinAndroidViewModel)

    testImplementation(Config.TestLibs.junit)

    androidTestImplementation(Config.TestLibs.androidJunitExtension)
    androidTestImplementation(Config.TestLibs.androidEspresso)
}