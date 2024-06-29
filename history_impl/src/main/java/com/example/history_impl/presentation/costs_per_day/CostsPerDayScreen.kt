package com.example.history_impl.presentation.costs_per_day

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_api.api_models.Currencies
import com.example.history_impl.R
import com.example.history_impl.presentation.history_list.Toolbar
import com.example.history_impl.presentation.models.CostItemUiModel
import com.example.history_impl.presentation.models.CostsPerDayScreenState
import com.example.history_impl.presentation.models.GroupedCostsUiModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CostsPerDayScreen(
    viewModel: CostsPerDayViewModel,
    groupedCosts: GroupedCostsUiModel?,
    onCurrencyChange: (Currencies, GroupedCostsUiModel) -> Unit
) {
  val screenState by viewModel.screenState.collectAsState()
  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
  val scope = rememberCoroutineScope()
  Column(
      modifier =
          Modifier.fillMaxSize()
              .background(color = colorResource(id = com.example.core.R.color.brand_color))
              .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
      horizontalAlignment = Alignment.CenterHorizontally) {
        Toolbar(title = stringResource(id = R.string.costs_per_day), onBackClick = viewModel::back)
        groupedCosts?.let {
          Text(text = groupedCosts.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
          Costs(costs = groupedCosts.costs, modifier = Modifier.weight(1f))
          when (val data = screenState) {
            CostsPerDayScreenState.Loading -> {
              Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.size(40.dp))
              }
            }
            is CostsPerDayScreenState.Currency -> {
              Converter(
                  date = groupedCosts.title,
                  currency = data.currency,
                  resultSum = data.sum,
                  onCurrencyChange = { scope.launch { sheetState.expand() } })
              if (sheetState.isVisible) {
                CurrenciesBottomSheet(
                    listCurrencies = viewModel.getListCurrencies(),
                    sheetState = sheetState,
                    chosenCurrency = data.currency,
                    doOnClick = { updatedCurrency ->
                      scope.launch { sheetState.hide() }
                      onCurrencyChange.invoke(updatedCurrency, groupedCosts)
                    })
              }
            }
            is CostsPerDayScreenState.Error -> {
              ErrorScreen { data.doOnError.invoke() }
            }
          }
        }
      }
}

@Composable
fun Costs(costs: List<CostItemUiModel>, modifier: Modifier) {
  LazyColumn(modifier = modifier.padding(top = 6.dp).fillMaxWidth()) {
    items(costs) { CostItem(item = it) }
  }
}

@Composable
fun CostItem(item: CostItemUiModel) {
  val comment = item.comments.ifEmpty { stringResource(id = R.string.no_comments) }
  Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
    Row {
      Text(text = item.category, fontSize = 16.sp, fontWeight = FontWeight.Bold)
      Spacer(modifier = Modifier.weight(1f))
      Text(text = item.sum.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold)
      Text(text = item.currency, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
    Text(
        text = comment,
        modifier = Modifier.fillMaxWidth().padding(top = 3.dp),
        color = colorResource(id = com.example.core.R.color.gray))
    HorizontalDivider(color = colorResource(id = com.example.core.R.color.teal_200))
  }
}

@Composable
fun ErrorScreen(doOnRepeat: () -> Unit) {
  Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
    Text(
        text = stringResource(id = R.string.error),
        fontSize = 18.sp,
        color = colorResource(id = com.example.core.R.color.red_600),
        textAlign = TextAlign.Center)
    TextButton(modifier = Modifier.padding(6.dp), onClick = doOnRepeat) {
      Text(
          text = stringResource(id = R.string.repeat),
          color = colorResource(id = com.example.core.R.color.teal_700))
    }
  }
}
