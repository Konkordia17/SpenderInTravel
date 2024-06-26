plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
}

android {
  namespace = "com.example.core"
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
  implementation(Libs.Android.core)
  implementation(Libs.Android.appcompat)
  implementation(Libs.Android.material)
  implementation(platform(Libs.Compose.bom))
  implementation(Libs.Compose.ui)
  implementation(Libs.Compose.graphics)
  implementation(Libs.Compose.preview)
  implementation(Libs.Compose.material3)

  api(project(":core_impl"))
  api(project(":core_api"))
}
