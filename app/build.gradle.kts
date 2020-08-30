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

    testOptions {
        unitTests.apply {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }

        animationsDisabled = true
    }

    lintOptions {
        disable = setOf("ObsoleteLintCustomCheck")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets.configureEach {
        java.setSrcDirs(java.srcDirs + File("src/$name/kotlin"))
    }
}

dependencies {
    implementation(Config.Libs.kotlin)
    implementation(Config.Libs.kotlinCoroutines)
    implementation(Config.Libs.androidCoreKtx)
    implementation(Config.Libs.androidAppCompat)
    implementation(Config.Libs.androidCardView)
    implementation(Config.Libs.androidConstraintLayout)
    implementation(Config.Libs.androidRecyclerView)
    implementation(Config.Libs.androidMaterialDesign)
    implementation(Config.Libs.jetpackViewModel)
    implementation(Config.Libs.jetpackLiveData)
    implementation(Config.Libs.jetpackRoom)
    implementation(Config.Libs.jetpackRoomKtx)
    kapt(Config.Libs.jetpackRoomCompiler)

    implementation(Config.Libs.koinAndroid)
    implementation(Config.Libs.koinAndroidViewModel)

    implementation(Config.Libs.squareRetrofit)
    implementation(Config.Libs.squareRetrofitGsonConverter)
    implementation(Config.Libs.squareRetrofitRxJava3Adapter)
    implementation(Config.Libs.rxJava3)
    implementation(Config.Libs.rxAndroid)

    testImplementation(Config.TestLibs.junit)
    testImplementation(Config.TestLibs.androidJunitExtension)
    testImplementation(Config.TestLibs.androidTestCore)
    testImplementation(Config.TestLibs.googleTruth)
    testImplementation(Config.TestLibs.robolectric)
    testImplementation(Config.TestLibs.mockk)

    androidTestImplementation(Config.TestLibs.androidJunitExtension)
    androidTestImplementation(Config.TestLibs.androidEspresso)
}