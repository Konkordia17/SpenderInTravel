package com.example.currency_converter_impl.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_api.api_models.Currencies
import com.example.core_api.api_models.CurrencyItemDataModel
import com.example.currency_converter.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyConverterScreen(viewModel: CurrencyConverterViewModel) {
  val screenState by viewModel.currencyScreenState.collectAsState()
  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
  val scope = rememberCoroutineScope()
  var chosenCurrency by remember { mutableStateOf<Pair<Currencies, Boolean>?>(null) }

  Column(
      modifier =
          Modifier.fillMaxSize()
              .navigationBarsPadding()
              .systemBarsPadding()
              .background(color = colorResource(id = com.example.core.R.color.brand_color))
              .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
      verticalArrangement = Arrangement.Top) {
        Toolbar(
            title = stringResource(id = R.string.currency_converter), onBackClick = viewModel::back)
        Image(
            modifier = Modifier.padding(top = 10.dp).weight(1f),
            painter = painterResource(id = com.example.core.R.drawable.ic_man_converts),
            contentDescription = "")

        when (val data = screenState) {
          CurrencyScreenState.LoadingState -> {
            LoadingIndicator()
          }
          is CurrencyScreenState.ChosenCurrenciesState -> {

            CurrenciesConverterBlock(
                data = data.uiModel.value,
                doOnCurrentValueChange = { isCurrencyFrom, value ->
                  viewModel.updateValue(isCurrencyFrom = isCurrencyFrom, value = value)
                },
                onChangeCurrencies = viewModel::changeCurrencies,
                onCurrencyClick = { currency, isCurrencyFrom ->
                  scope.launch { sheetState.expand() }
                  chosenCurrency = currency to isCurrencyFrom
                })

            if (sheetState.isVisible) {
              CurrenciesBottomSheet(
                  listCurrencies = viewModel.getListCurrencies(),
                  sheetState = sheetState,
                  chosenCurrency = chosenCurrency?.first,
                  doOnClick = { updatedCurrency ->
                    scope.launch { sheetState.hide() }
                    chosenCurrency?.let { (_, isCurrencyFrom) ->
                      viewModel.changeCurrency(
                          currency = updatedCurrency, isCurrencyFrom = isCurrencyFrom)
                    }
                  })
            }
          }
          is CurrencyScreenState.Error -> {
            ErrorBlock { data.doOnError.invoke() }
          }
        }
      }
}

@Composable
fun CurrenciesConverterBlock(
    data: CurrenciesConverterUiModel,
    doOnCurrentValueChange: (Boolean, String) -> Unit,
    onChangeCurrencies: () -> Unit,
    onCurrencyClick: (Currencies, Boolean) -> Unit,
) {
  Row(
      modifier = Modifier.fillMaxWidth().padding(16.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center) {
        Column(modifier = Modifier.padding(bottom = 20.dp).weight(1f)) {
          CurrencyInputField(
              modifier = Modifier,
              currency = data.currencyFrom.name,
              amount = data.currencyFromResult.toString(),
              onValueChange = { doOnCurrentValueChange.invoke(true, it) },
              onCurrencyClick = { onCurrencyClick.invoke(data.currencyFrom, true) },
              isCurrentCurrency = true)
          CurrencyInputField(
              modifier = Modifier.padding(top = 10.dp),
              currency = data.currencyTo.name,
              amount = data.currencyToResult.toString(),
              onValueChange = { doOnCurrentValueChange.invoke(false, it) },
              onCurrencyClick = { onCurrencyClick.invoke(data.currencyTo, false) })
        }

        Box(
            modifier =
                Modifier.padding(start = 8.dp).size(40.dp).clickable {
                  onChangeCurrencies.invoke()
                }) {
              Image(
                  painter = painterResource(id = com.example.core.R.drawable.ic_change),
                  contentDescription = "")
            }
      }
}

@Composable
fun CurrencyInputField(
    modifier: Modifier,
    amount: String,
    currency: String,
    onValueChange: (String) -> Unit,
    onCurrencyClick: () -> Unit,
    isCurrentCurrency: Boolean = false
) {
  var currentValue by remember { mutableStateOf(amount) }
  LaunchedEffect(amount) {
    if (currentValue != amount) {
      currentValue = amount
    }
  }

  Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
    Text(
        modifier = Modifier.padding(end = 4.dp).clickable { onCurrencyClick.invoke() },
        text = currency,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        color = colorResource(id = com.example.core.R.color.black))

    OutlinedTextField(
        value = currentValue,
        onValueChange = { newText ->
          if (newText.isEmpty() || newText.matches(Regex("^\\d*\\.?\\d*\$"))) {
            var sanitizedText = newText

            if (!newText.contains(".") && newText.endsWith("0") && newText.length > 1) {
              sanitizedText = newText.dropLast(1) + "." + newText.last()
            }
            currentValue = sanitizedText
            onValueChange.invoke(sanitizedText)
          }
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        modifier = Modifier.width(240.dp)
            .onFocusChanged { focusState ->
            if (focusState.isFocused && currentValue == "0.0") {
                currentValue = ""
            }
        },
        shape = RoundedCornerShape(10.dp),
        colors =
            TextFieldDefaults.colors(
                unfocusedTextColor = colorResource(id = com.example.core.R.color.blue),
                focusedTextColor = colorResource(id = com.example.core.R.color.purple_700),
                unfocusedContainerColor = colorResource(id = com.example.core.R.color.brand_color),
                focusedContainerColor = colorResource(id = com.example.core.R.color.brand_color),
                unfocusedIndicatorColor = colorResource(id = com.example.core.R.color.blue),
                focusedIndicatorColor = colorResource(id = com.example.core.R.color.purple_700)))
    if (isCurrentCurrency) {
      Icon(
          modifier = Modifier.size(20.dp).clickable {
              onValueChange.invoke("0")
                                                    },
          painter = painterResource(id = R.drawable.ic_close),
          contentDescription = "")
    }
  }
}

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
            modifier = Modifier.navigationBarsPadding().statusBarsPadding().fillMaxSize(),
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

@Composable
fun LoadingIndicator() {
  Box(modifier = Modifier.fillMaxWidth().padding(40.dp), contentAlignment = Alignment.Center) {
    CircularProgressIndicator(modifier = Modifier.size(40.dp))
  }
}

@Composable
fun ErrorBlock(doOnError: () -> Unit) {
  Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
    Text(text = stringResource(id = R.string.error_message))

    OutlinedButton(
        border =
            BorderStroke(
                width = 2.dp, color = colorResource(id = com.example.core.R.color.purple_700)),
        modifier = Modifier.padding(top = 12.dp),
        onClick = doOnError) {
          Text(text = stringResource(id = R.string.repeat))
        }
  }
}

@Composable
fun Toolbar(title: String, onBackClick: () -> Unit) {
  Row(
      modifier = Modifier.fillMaxWidth().height(56.dp).padding(vertical = 10.dp),
      verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier.size(30.dp).clickable { onBackClick.invoke() },
            painter = painterResource(id = com.example.core_api.R.drawable.ic_back),
            contentDescription = "")
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp))
      }
}
