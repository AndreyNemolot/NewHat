plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
}

kapt {
    correctErrorTypes = true
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":utilities"))

    implementation(AppDependencies.appLibraries)
    implementation(AppDependencies.toothpick)
    kapt(AppDependencies.toothpickKapt)
    implementation(AppDependencies.cicerone)
    implementation(AppDependencies.fragment)
    implementation(AppDependencies.epoxy)
    kapt(AppDependencies.epoxyKapt)
    implementation(AppDependencies.flexBox)
    implementation(AppDependencies.lifecycle)
    implementation(AppDependencies.lifecycleArch)
    api(AppDependencies.coroutines)
    implementation(AppDependencies.recyclerGesture)

}