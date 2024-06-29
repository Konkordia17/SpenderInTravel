plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt")
}

android {
  namespace = "com.example.core_api"
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
}
