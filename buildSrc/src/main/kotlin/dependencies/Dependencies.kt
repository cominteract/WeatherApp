import Versions.appCompatVersion
import Versions.archCoreTestVersion
import Versions.constraintVersion
import Versions.coroutineCore
import Versions.coroutineTestVersion
import Versions.dataStoreVersion
import Versions.expressoCoreVersion
import Versions.hiltAndroidXVersion
import Versions.hiltDaggerTestVersion
import Versions.hiltVersion
import Versions.hiltViewModelVersion
import Versions.httpLoggingVersion
import Versions.okHttpVersion
import Versions.junitRunnerVersion
import Versions.junitVersion
import Versions.kotlinSerializationConverterVersion
import Versions.kotlinSerializationJsonVersion
import Versions.kotlinSerializationPropertiesVersion
import Versions.legacySupportVersion
import Versions.liveDataVersion
import Versions.materialVersion
import Versions.roomVersion
import Versions.runnerVersion
import Versions.lifecycleRunTimeKtxVersion
import Versions.navigationVersion
import Versions.retrofitVersion
import Versions.viewModelKtxVersion
import Versions.workVersion

object Versions {
    val versionName = "7.0.15" // X.Y.Z; X = Major, Y = minor, Z = Patch level
    private val versionCodeBase = 70150 // XYYZZM; M = Module (tv, mobile)
    val versionCodeMobile = versionCodeBase + 3

    const val COMPILE_SDK = 30
    const val TARGET_SDK = 30
    const val MIN_SDK = 23
    const val APPLICATION_ID = "com.ainsigne.weatherapp"

    const val ANDROID_GRADLE_PLUGIN = "4.2.2"
    const val KOTLIN = "1.5.10"
    const val NAVIGATION = "2.3.5"
    const val legacySupportVersion = "1.0.0"
    const val okHttpVersion = "4.9.0"
    const val httpLoggingVersion = "4.9.0"

    // unit test version
    const val junitVersion = "4.13.2"
    const val runnerVersion = "1.0.2"
    const val expressoCoreVersion = "3.0.2"
    const val coroutineTestVersion = "1.5.0"
    const val archCoreTestVersion = "2.1.0"
    const val hiltDaggerTestVersion = "2.28-alpha"
    const val junitRunnerVersion = "1.4.0"

    const val kotlinSerializationPropertiesVersion = "1.3.0"
    const val kotlinSerializationJsonVersion = "1.3.0"
    const val kotlinSerializationConverterVersion = "0.8.0"

    // hilt dagger
    const val hiltVersion = "2.38.1"
    const val hiltViewModelVersion = "1.0.0-alpha03"
    const val hiltAndroidXVersion = "1.0.0"
    const val hiltTestVersion = "2.28-alpha"

    // utilities
    const val materialVersion = "1.4.0"
    const val constraintVersion = "2.1.0"
    const val appCompatVersion = "1.3.1"
    const val roomVersion = "2.3.0"
    const val coroutineCore = "1.5.2"
    const val navigationVersion = "2.3.5"
    const val viewModelKtxVersion = "2.3.0"
    const val lifecycleRunTimeKtxVersion = "2.3.1"
    const val workVersion = "2.7.0-alpha05"
    const val liveDataVersion = "2.3.1"
    const val dataStoreVersion = "1.0.0"
    const val retrofitVersion = "2.9.0"
}

object Libraries {
    //android support
    const val androidLegacySupport = "androidx.legacy:legacy-support-v4:$legacySupportVersion"
    const val materialDesign = "com.google.android.material:material:$materialVersion"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintVersion"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
    const val supportAnnotation = "com.android.support:support-annotations:28.0.0"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$viewModelKtxVersion"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleRunTimeKtxVersion"
    const val appInspectorRuntime = "androidx.work:work-runtime-ktx:$workVersion"
    const val coreKtx = "androidx.core:core-ktx:1.6.0"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$liveDataVersion"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:1.4.0-alpha03"

    //Navigation
    const val navFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    const val navUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"

    //okHttp
    const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
    const val httpLogging = "com.squareup.okhttp3:logging-interceptor:$httpLoggingVersion"

    //local storage
    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    const val kaptRoom = "androidx.room:room-compiler:$roomVersion"

    //retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"

    //kotlin serialization
    const val kotlinSerializationConverter =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:$kotlinSerializationConverterVersion"
    const val kotlinSerializationJson =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationJsonVersion"
    const val kotlinSerializationProperties =
        "org.jetbrains.kotlinx:kotlinx-serialization-properties:$kotlinSerializationPropertiesVersion"

    //coroutine
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineCore"
    const val coroutinesKotlinx = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineCore"

    // unit test
    const val junitTestImp = "junit:junit:$junitVersion"
    const val runnerAndroidTestImp = "com.android.support.test:runner:$runnerVersion"

    const val expressoCoreAnroidTestImp = "com.android.support.test.espresso:espresso-core:$expressoCoreVersion"
    const val coroutineTestImp = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineTestVersion"
    const val archCoreTestImp = "androidx.arch.core:core-testing:$archCoreTestVersion"
    const val hiltDaggerTestImp = "com.google.dagger:hilt-android-testing:$hiltDaggerTestVersion"
    const val junitRunner = "androidx.test:runner:$junitRunnerVersion"

    const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineTestVersion"

    // dependency Injection
    const val hiltDagger = "com.google.dagger:hilt-android:$hiltVersion"
    const val hiltDaggerKaptCompiler = "com.google.dagger:hilt-compiler:$hiltVersion"

    // dependency injection viewModel
    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$hiltViewModelVersion"

    const val kaptAndroidXCompiler = "androidx.hilt:hilt-compiler:$hiltAndroidXVersion"

    // datastore
    const val dataStorePreference = "androidx.datastore:datastore-preferences:${dataStoreVersion}"


}
