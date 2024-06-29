package com.example.history_impl.presentation.costs_per_day

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_api.api_models.Currencies
import com.example.core_api.api_models.CurrencyItemDataModel
import kotlinx.collections.immutable.ImmutableList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrenciesBottomSheet(
    listCurrencies: ImmutableList<CurrencyItemDataModel>,
    sheetState: SheetState,
    chosenCurrency: Currencies?,
    doOnClick: (Currencies) -> Unit
) {

  ModalBottomSheet(
      containerColor = colorResource(id = com.example.core.R.color.brand_color),
      onDismissRequest = {},
      sheetState = sheetState) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
              Text(
                  text = stringResource(id = com.example.core_api.R.string.choose_currency),
                  textAlign = TextAlign.Center,
                  fontSize = 20.sp,
                  color = colorResource(id = com.example.core.R.color.black),
                  fontWeight = FontWeight.Bold)
              LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items = listCurrencies) { currency ->
                  Row(
                      modifier =
                          Modifier.fillMaxWidth().padding(12.dp).clickable {
                            doOnClick.invoke(currency.currency)
                          },
                      verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            currency.currency.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium)
                        Text(
                            modifier = Modifier.weight(1f).padding(start = 10.dp),
                            text = currency.currency.description)
                        if (chosenCurrency == currency.currency) {
                          Image(
                              modifier = Modifier.size(40.dp),
                              painter =
                                  painterResource(
                                      id = com.example.core_api.R.drawable.ic_arrow_down),
                              contentDescription = "")
                        }
                      }
                  HorizontalDivider(modifier = Modifier.fillMaxWidth())
                }
              }
            }
      }
}
