import Versions.COMPILE_SDK
import Versions.TARGET_SDK
import Versions.MIN_SDK

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(COMPILE_SDK)

    defaultConfig {
        minSdkVersion(MIN_SDK)
        targetSdkVersion(TARGET_SDK)
        // apply the pro guard rules for library
        consumerProguardFiles("consumer-rules.pro")
    }
    flavorDimensions("weatherapp")
    productFlavors {
        create("prod") {
            dimension = "weatherapp"
            buildConfigField(
                "String",
                configuration.BuildConfigKeys.API_BASE_URL,
                "\"${configuration.Staging.SERVER_PREFIX}\""
            )
            buildConfigField(
                "String",
                configuration.BuildConfigKeys.API_KEY,
                "\"${configuration.Staging.WEATHER_API_KEY}\""
            )
        }
        create("stg") {
            dimension = "weatherapp"
            buildConfigField(
                "String",
                configuration.BuildConfigKeys.API_BASE_URL,
                "\"${configuration.Staging.SERVER_PREFIX}\""
            )
            buildConfigField(
                "String",
                configuration.BuildConfigKeys.API_KEY,
                "\"${configuration.Staging.WEATHER_API_KEY}\""
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    testOptions.unitTests.isIncludeAndroidResources = true
}


dependencies {

    api(project(":domain"))
    api(project(":utilities"))

    implementation(Libraries.coroutinesCore)
    implementation(Libraries.coroutinesKotlinx)

    implementation(Libraries.kotlinSerializationProperties)
    implementation(Libraries.kotlinSerializationJson)
    implementation(Libraries.okHttp)
    implementation(Libraries.httpLogging)
    implementation(Libraries.retrofit)
    implementation(Libraries.kotlinSerializationConverter)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation(Libraries.hiltDagger)
    kapt(Libraries.hiltDaggerKaptCompiler)


    testImplementation(Libraries.junitTestImp)
}