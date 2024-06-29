plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt")
}

android {
  namespace = "com.example.activity"
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
  kapt(Libs.Dagger.dagger_kapt)
  implementation(Libs.Android.appcompat)
  implementation(Libs.Dagger.dagger)
  implementation(Libs.Navigation.cicerone)
  implementation(platform(Libs.OkHttp.bom))

  implementation(Libs.OkHttp.okhttp)
  implementation(Libs.OkHttp.interceptor)
  implementation(Libs.Retrofit.retrofit)
  implementation(Libs.Retrofit.converter_gson)

  implementation(project(":core_api"))
  implementation(project(":core_api"))
  implementation(project(":splash_api"))
}
