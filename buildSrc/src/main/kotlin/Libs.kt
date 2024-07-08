object Libs {

  object Dagger {
    private const val version = "2.51.1"
    const val dagger = "com.google.dagger:dagger:$version"
    const val dagger_kapt = "com.google.dagger:dagger-compiler:$version"
  }

  object Compose {
    const val bom = "androidx.compose:compose-bom:2023.08.00"
    const val ui_tooling = "androidx.compose.ui:ui-tooling"
    const val junit4 = "androidx.compose.ui:ui-test-junit4:1.6.0"
    const val test = "androidx.compose.ui:ui-test-manifest"
    const val junit4_android = "androidx.compose.ui:ui-test-junit4-android:1.6.0"
    const val ui = "androidx.compose.ui:ui"
    const val graphics = "androidx.compose.ui:ui-graphics"
    const val preview = "androidx.compose.ui:ui-tooling-preview"
    const val material3 = "androidx.compose.material3:material3:1.2.1"
    const val activity = "androidx.activity:activity-compose:1.9.0"
  }

  object Android {
    const val core = "androidx.core:core-ktx:1.13.1"
    const val appcompat = "androidx.appcompat:appcompat:1.6.1"
    const val material = "com.google.android.material:material:1.12.0"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.8.0"
  }

  object Tests {
    private const val jupiter_version = "5.8.2"

    const val junit = "junit:junit:4.13.2"
    const val ext_junit = "androidx.test.ext:junit:1.1.5"
    const val espresso = "androidx.test.espresso:espresso-core:3.5.0"
    const val jupiter_api = "org.junit.jupiter:junit-jupiter-api:$jupiter_version"
    const val jupiter_engine = "org.junit.jupiter:junit-jupiter-engine:$jupiter_version"
    const val jupiter_params = "org.junit.jupiter:junit-jupiter-params:$jupiter_version"
    const val jupiter_vintage_engine = "org.junit.jupiter:junit-vintage-engine:$jupiter_version"
    const val mockk = "io.mockk:mockk:1.12.0"
    const val mockk_android = "io.mockk:mockk-android:1.12.0"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1"
  }

  object Lottie {
    private const val version = "6.0.0"
    const val lottie = "com.airbnb.android:lottie:$version"
    const val compose = "com.airbnb.android:lottie-compose:$version"
  }

  object Navigation {
    const val cicerone = "com.github.terrakok:cicerone:7.1"
  }

  object OkHttp {
    const val bom = "com.squareup.okhttp3:okhttp-bom:4.12.0"
    const val okhttp = "com.squareup.okhttp3:okhttp"
    const val interceptor = "com.squareup.okhttp3:logging-interceptor"
  }

  object Retrofit {
    private const val version = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$version"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:$version"
  }

  object Kotlin {
    const val immutable = "org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5"
    const val reflect = "org.jetbrains.kotlin:kotlin-reflect:1.5.31"
  }

  object Room {
    private const val version = "2.6.1"
    const val room_runtime = "androidx.room:room-runtime:$version"
    const val compiler = "androidx.room:room-compiler:$version"
    const val room_ktx = "androidx.room:room-ktx:$version"
  }
}
