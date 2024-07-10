import com.github.triplet.gradle.androidpublisher.ReleaseStatus

plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt")
  id("com.github.triplet.play") version "3.8.4"
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
    playConfigs {
      register("SpenderInTravel") { enabled.set(true) }
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

    packaging {
      resources {
        excludes += setOf("/META-INF/AL2.0", "/META-INF/LGPL2.1", "META-INF/INDEX.LIST", "META-INF/DEPENDENCIES")
      }
    }
}

  dependencies {
    implementation(Libs.Compose.junit4_android)
    implementation(Libs.Android.appcompat)
    kapt(Libs.Dagger.dagger_kapt)
    implementation(Libs.Dagger.dagger)
    implementation(Libs.Navigation.cicerone)
    implementation(platform(Libs.OkHttp.bom))
    implementation(Libs.OkHttp.okhttp)
    implementation(Libs.OkHttp.interceptor)
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.converter_gson)
    testImplementation(Libs.Tests.jupiter_api)
    testRuntimeOnly(Libs.Tests.jupiter_engine)
    testImplementation(Libs.Tests.jupiter_params)

    testImplementation(Libs.Tests.mockk)
    androidTestImplementation(Libs.Tests.mockk_android)
    testImplementation(Libs.Tests.junit)
    androidTestImplementation(Libs.Tests.ext_junit)
    androidTestImplementation(Libs.Tests.espresso)
    androidTestImplementation(Libs.Compose.junit4)

    debugImplementation(Libs.Compose.test)

    implementation(Libs.Google.oauth_client)
    implementation(Libs.Google.api_client)
    implementation(Libs.Google.androidpublisher)

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

  play {
    serviceAccountCredentials.set(
        file("/Users/nimas-k-a/Downloads/spenderintravel-7a3769f71775.json"))
    track.set("production")
    userFraction.set(0.5)
    updatePriority.set(2)
    defaultToAppBundles.set(true)
    releaseStatus.set(ReleaseStatus.DRAFT)
  }


  tasks.register("publishAppToPlayStore") {
    group = "publishing"
    description = "Publishes the app to the Google Play Store"

    dependsOn("publishReleaseBundle")

    doLast {
      println("App published to Google Play Store!")
    }
  }
}
