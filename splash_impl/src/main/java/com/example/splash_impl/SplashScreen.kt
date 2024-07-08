package com.example.splash_impl

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onFinishAnimation: () -> Unit) {
  LaunchedEffect(true) {
    delay(3000)
    onFinishAnimation.invoke()
  }

  val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_animation))

  Box(
      contentAlignment = Alignment.Center,
      modifier =
          Modifier.fillMaxSize()
              .background(color = colorResource(id = R.color.brand_color))
              .clickable { onFinishAnimation.invoke() }
              .testTag("SplashScreenBox")) {
        LottieAnimation(
            composition = composition, modifier = Modifier.fillMaxSize())
      }
}
