package com.example.main.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.main.R

@Composable
fun MenuScreen(onMenuClick: (MenuScreens) -> Unit) {
  Column(
      modifier =
          Modifier.fillMaxSize()
              .background(color = colorResource(id = R.color.brand_color))
              .testTag("MenuScreen")
              .paint(
                  painter = painterResource(id = R.drawable.ic_finance),
                  contentScale = ContentScale.Crop),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center) {
        MenuButton(title = R.string.cost_accounting) {
          onMenuClick.invoke(MenuScreens.COST_ACCOUNTING)
        }
        MenuButton(title = R.string.history) { onMenuClick.invoke(MenuScreens.HISTORY) }
        MenuButton(title = R.string.currency_converter) {
          onMenuClick.invoke(MenuScreens.CURRENCY_CONVERTER)
        }
      }
}

@Composable
fun MenuButton(title: Int, doOnClick: () -> Unit) {
  OutlinedButton(
      onClick = doOnClick,
      shape = RoundedCornerShape(20.dp),
      border = BorderStroke(width = 2.dp, color = colorResource(id = R.color.teal_700)),
      modifier = Modifier.testTag(stringResource(id = title)).padding(20.dp),
      colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)) {
        Text(
            text = stringResource(id = title),
            color = colorResource(id = R.color.teal_700),
            modifier = Modifier.padding(10.dp))
      }
}
