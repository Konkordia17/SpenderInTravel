package com.example.currency_converter_impl.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.currency_converter_impl.di.DaggerCurrencyConverterComponent
import com.example.network.NetworkComponentProvider

@Composable
fun CurrencyConverterScreen(networkComponentProvider: NetworkComponentProvider) {

  val dependency = remember {
    DaggerCurrencyConverterComponent.factory().create(networkComponentProvider)
  }

  val viewModel = remember { dependency.injectViewModel() }
  val lifecycleOwner = LocalLifecycleOwner.current
  DisposableEffect(lifecycleOwner) {
    val observer = LifecycleEventObserver { _, event ->
      if (event == Lifecycle.Event.ON_DESTROY) {
        viewModel.clear()
      }
    }

    lifecycleOwner.lifecycle.addObserver(observer)

    onDispose {
      viewModel.clear()
      lifecycleOwner.lifecycle.removeObserver(observer)
    }
  }
  Column(
      modifier =
          Modifier.fillMaxSize()
              .background(color = colorResource(id = com.example.core.R.color.brand_color))) {
        Image(
            modifier = Modifier.height(400.dp).width(300.dp),
            painter = painterResource(id = com.example.core.R.drawable.ic_man_converts),
            contentDescription = "")
      }
}
