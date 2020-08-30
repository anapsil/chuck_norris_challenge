object Config {
    const val kotlinVersion = "1.4.0"
    const val detektPluginVersion = "1.12.0-RC1"
    const val lifecycleVersion = "2.2.0"
    const val koinVersion = "2.1.6"
    const val mockkVersion = "1.10.0"
    const val androidTestVersion = "1.1.1"
    const val roomVersion = "2.2.5"
    const val retrofitVersion = "2.9.0"

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
        const val jetpackRoom = "androidx.room:room-runtime:$roomVersion"
        const val jetpackRoomCompiler = "androidx.room:room-compiler:$roomVersion"
        const val jetpackRoomKtx = "androidx.room:room-ktx:$roomVersion"
        const val jetpackRoomRxJava2 = "androidx.room:room-rxjava2:$roomVersion"
        const val koinCore = "org.koin:koin-core:$koinVersion"
        const val koinAndroid = "org.koin:koin-android:$koinVersion"
        const val koinAndroidViewModel = "org.koin:koin-androidx-viewmodel:$koinVersion"
        const val squareRetrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val squareRetrofitGsonConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val squareRetrofitRxJava2Adapter = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
        const val squareOkHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:3.14.9"
        const val rxJava2 = "io.reactivex.rxjava2:rxjava:2.2.19"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
    }

    object TestLibs {
        const val junit = "junit:junit:4.12"
        const val androidJunitExtension = "androidx.test.ext:junit:$androidTestVersion"
        const val androidTestCore = "androidx.test:core:$androidTestVersion"
        const val googleTruth = "com.google.truth:truth:0.42"
        const val androidEspresso = "androidx.test.espresso:espresso-core:3.2.0"
        const val robolectric = "org.robolectric:robolectric:4.3"
        const val mockk = "io.mockk:mockk:$mockkVersion"
    }
}