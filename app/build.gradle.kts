plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt")
}

android {
  namespace = "com.example.spenderintravel"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.example.spenderintravel"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables { useSupportLibrary = true }
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
  packaging { resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" } }
}

dependencies {
  kapt(Libs.Dagger.dagger_kapt)
  implementation(Libs.Dagger.dagger)
  implementation(Libs.Navigation.cicerone)
  implementation(platform(Libs.OkHttp.bom))
  implementation(Libs.OkHttp.okhttp)
  implementation(Libs.OkHttp.interceptor)
  implementation(Libs.Retrofit.retrofit)
  implementation(Libs.Retrofit.converter_gson)

  api(project(":core"))

  implementation(project(":menu_impl"))
  implementation(project(":menu_api"))

  implementation(project(":core_api"))
  implementation(project(":activity"))
  implementation(project(":splash_api"))
  implementation(project(":splash_impl"))
  implementation(project(":currency_converter_api"))
  implementation(project(":currency_converter_impl"))
  implementation(project(":network_impl"))
  implementation(project(":cost_accounting_api"))
  implementation(project(":cost_accounting_impl"))
  implementation(project(":history_impl"))
  implementation(project(":history_api"))
}

kapt { correctErrorTypes = true }
