package com.example.history_impl.presentation.costs_per_day

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_api.api_models.Currencies
import com.example.history_impl.R

@Composable
fun Converter(date: String, currency: Currencies, resultSum: Double, onCurrencyChange: () -> Unit) {
  Column(
      modifier =
          Modifier.fillMaxWidth()
              .border(width = 1.dp, color = colorResource(id = com.example.core.R.color.teal_200)),
      horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.convert_by_date, date),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = colorResource(id = com.example.core.R.color.teal_700))
        HorizontalDivider(
            color = colorResource(id = com.example.core.R.color.teal_200),
            modifier = Modifier.padding(top = 8.dp))
        Row(
            Modifier.padding(top = 8.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
              Text(
                  text = stringResource(id = R.string.currency),
                  fontSize = 18.sp,
                  modifier = Modifier.weight(0.5f),
                  textAlign = TextAlign.Center)
              Spacer(
                  modifier =
                      Modifier.background(colorResource(id = com.example.core.R.color.teal_200))
                          .width(2.dp))
              Text(
                  textAlign = TextAlign.Center,
                  text = currency.name,
                  fontSize = 18.sp,
                  modifier = Modifier.weight(0.5f))
            }
        HorizontalDivider(color = colorResource(id = com.example.core.R.color.teal_200))

        Row(
            Modifier.padding(top = 8.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
              Text(
                  textAlign = TextAlign.Center,
                  text = stringResource(id = R.string.sum),
                  fontSize = 18.sp,
                  modifier = Modifier.weight(0.5f))
              Spacer(
                  modifier =
                      Modifier.background(colorResource(id = com.example.core.R.color.teal_200))
                          .width(2.dp))
              Text(
                  textAlign = TextAlign.Center,
                  text = resultSum.toString(),
                  fontSize = 18.sp,
                  modifier = Modifier.weight(0.5f))
            }
        HorizontalDivider(
            color = colorResource(id = com.example.core.R.color.teal_200),
            modifier = Modifier.padding(bottom = 8.dp))
        TextButton(onClick = onCurrencyChange) {
          Text(
              text = stringResource(id = R.string.choose_another_currency),
              color = colorResource(id = com.example.core.R.color.teal_700))
        }
      }
}
