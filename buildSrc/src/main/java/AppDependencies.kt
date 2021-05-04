import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {
    //std lib
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    //android ui
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val material = "com.google.android.material:material:${Versions.material}"
    val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    val epoxy = "com.airbnb.android:epoxy:${Versions.epoxy}"
    val epoxyKapt = "com.airbnb.android:epoxy-processor:${Versions.epoxy}"
    val lifecycle = arrayListOf<String>().apply {
        add("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}")
        add("androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}")
        add("androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}")
        add("androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}")
    }
    val lifecycleArch = "android.arch.lifecycle:extensions:${Versions.lifecycleArch}"
    val flexBox = "com.google.android:flexbox:${Versions.flexBox}"

    //DI
    val toothpick = "com.github.stephanenicolas.toothpick:ktp:${Versions.toothpick}"
    val toothpickKapt =
        "com.github.stephanenicolas.toothpick:toothpick-compiler:${Versions.toothpick}"

    //Cicerone
    val cicerone = "ru.terrakok.cicerone:cicerone:${Versions.cicerone}"

    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    val appLibraries = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(coreKtx)
        add(appcompat)
        add(constraintLayout)
        add(material)
    }

    val recyclerGesture = "com.github.WilliBoelke:simple-recycler-view-swipe-gestures:v1.1"
}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}
