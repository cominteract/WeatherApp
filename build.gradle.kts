// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    val kotlinVersion by extra("1.5.10")
    val hiltVersion = "2.38.1"
    val gradleVersion = Versions.ANDROID_GRADLE_PLUGIN
    val navigationVersion = "2.3.5"
    val serializationVersion = "1.5.0"

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:$gradleVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
        classpath ("org.jetbrains.kotlin:kotlin-serialization:$serializationVersion")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
