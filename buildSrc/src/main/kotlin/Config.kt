object Config {
    const val kotlinVersion = "1.4.0"
    const val detektPluginVersion = "1.12.0-RC1"
    const val lifecycleVersion = "2.2.0"
    const val koinVersion = "2.1.6"
    const val mockkVersion = "1.10.0"
    const val androidTestVersion = "1.1.1"

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
        const val androidCardView = "androidx.cardview:cardview:1.0.0"
        const val androidConstraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val androidRecyclerView = "androidx.recyclerview:recyclerview:1.1.0"
        const val androidMaterialDesign = "com.google.android.material:material:1.1.0"
        const val jetpackViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val jetpackLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        const val koinCore = "org.koin:koin-core:$koinVersion"
        const val koinAndroid = "org.koin:koin-android:$koinVersion"
        const val koinAndroidViewModel = "org.koin:koin-androidx-viewmodel:$koinVersion"
    }

    object TestLibs {
        const val junit = "junit:junit:4.12"
        const val androidJunitExtension = "androidx.test.ext:junit:$androidTestVersion"
        const val androidTruthExtension = "androidx.test.ext:truth:$androidTestVersion"
        const val androidTestCore = "androidx.test:core:$androidTestVersion"
        const val googleTruth = "com.google.truth:truth:0.42"
        const val androidEspresso = "androidx.test.espresso:espresso-core:3.2.0"
        const val robolectric = "org.robolectric:robolectric:4.3"
        const val mockk = "io.mockk:mockk:$mockkVersion"
    }
}