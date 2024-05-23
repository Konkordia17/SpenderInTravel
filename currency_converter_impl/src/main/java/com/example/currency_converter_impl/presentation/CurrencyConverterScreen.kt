package com.example.currency_converter_impl.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CurrencyConverterScreen(
    viewModel: CurrencyConverterViewModel
) {
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
