plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt")
  id("kotlin-parcelize")
  id("org.jetbrains.kotlinx.kover")
}

android {
  namespace = "com.example.cost_accounting"
  compileSdk = 34

  defaultConfig {
    minSdk = 24

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }
  testOptions { unitTests.isIncludeAndroidResources = true }

  buildTypes {
      debug {
         enableUnitTestCoverage = true
      }
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      enableUnitTestCoverage = false
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions { jvmTarget = "1.8" }

  composeOptions { kotlinCompilerExtensionVersion = "1.5.1" }

  buildFeatures { compose = true }
}
kover {
  filters {
    classes {
      excludes += listOf(
        "**/R.class",
        "**/R$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*"
      )
    }
  }
}

dependencies {
  implementation(Libs.Dagger.dagger)
  kapt(Libs.Dagger.dagger_kapt)

  implementation(Libs.Navigation.cicerone)

  implementation(platform(Libs.OkHttp.bom))
  implementation(Libs.OkHttp.okhttp)
  implementation(Libs.OkHttp.interceptor)

  implementation(Libs.Retrofit.retrofit)
  implementation(Libs.Retrofit.converter_gson)

  implementation(Libs.Room.room_runtime)
  annotationProcessor(Libs.Room.compiler)
  kapt(Libs.Room.compiler)
  implementation(Libs.Room.room_ktx)

  testImplementation(Libs.Tests.junit)
  androidTestImplementation(Libs.Tests.ext_junit)
  androidTestImplementation(Libs.Tests.espresso)

  implementation(Libs.Android.lifecycle)
  implementation(Libs.Android.core)
  implementation(Libs.Android.appcompat)
  implementation(Libs.Android.material)

  implementation(platform(Libs.Compose.bom))
  implementation(Libs.Compose.ui)
  implementation(Libs.Compose.graphics)
  implementation(Libs.Compose.preview)
  implementation(Libs.Compose.material3)
  androidTestImplementation(platform(Libs.Compose.bom))
  androidTestImplementation(Libs.Compose.junit4)
  debugImplementation(Libs.Compose.ui_tooling)
  debugImplementation(Libs.Compose.test)
  implementation(platform(Libs.Compose.bom))
  implementation(Libs.Kotlin.immutable)

  testImplementation(Libs.Tests.jupiter_api)
  testRuntimeOnly(Libs.Tests.jupiter_engine)
  testImplementation(Libs.Tests.jupiter_params)

  testImplementation(Libs.Tests.mockk)
  androidTestImplementation(Libs.Tests.mockk_android)
  testImplementation(Libs.Tests.coroutines)

  implementation(project(":core"))
  implementation(project(":cost_accounting_api"))
}
