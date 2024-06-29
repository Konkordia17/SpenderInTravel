plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt")
  id("kotlin-parcelize")
}

android {
  namespace = "com.example.currency_converter"
  compileSdk = 34

  defaultConfig {
    minSdk = 24

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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

dependencies {
  implementation(Libs.Dagger.dagger)
  kapt(Libs.Dagger.dagger_kapt)

  implementation(Libs.Navigation.cicerone)

  implementation(platform(Libs.OkHttp.bom))
  implementation(Libs.OkHttp.okhttp)
  implementation(Libs.OkHttp.interceptor)

  implementation(Libs.Retrofit.retrofit)
  implementation(Libs.Retrofit.converter_gson)

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

  implementation(project(":core"))
  implementation(project(":currency_converter_api"))
}
