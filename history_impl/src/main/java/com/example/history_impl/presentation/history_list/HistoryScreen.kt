package com.example.history_impl.presentation.history_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.history_impl.R
import com.example.history_impl.presentation.models.GroupedCostsUiModel
import kotlinx.collections.immutable.ImmutableList

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryScreen(viewModel: HistoryViewModel) {

  Column(
      modifier =
          Modifier.fillMaxSize()
              .background(color = colorResource(id = com.example.core.R.color.brand_color))) {
        val state by viewModel.historyScreenState.collectAsState()
        Toolbar(
            modifier = Modifier.padding(horizontal = 16.dp),
            title = stringResource(id = R.string.history),
            onBackClick = viewModel::back)

        when (val screen = state) {
          HistoryScreenState.Loading -> {}
          is HistoryScreenState.HistoryList -> {
            HistoryList(costs = screen.costs, onItemClick = viewModel::openCostsPerDay)
          }
          HistoryScreenState.Error -> {}
        }
      }
}

@Composable
fun HistoryList(
    costs: ImmutableList<GroupedCostsUiModel>,
    onItemClick: (GroupedCostsUiModel) -> Unit
) {
  if (costs.isEmpty()) {
    EmptyHistoryList()
  } else {
    LazyColumn { items(costs) { HistoryItem(item = it, onItemClick = onItemClick) } }
  }
}

@Composable
fun HistoryItem(item: GroupedCostsUiModel, onItemClick: (GroupedCostsUiModel) -> Unit) {
  Column(modifier = Modifier.clickable { onItemClick.invoke(item) }) {
    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
      Text(text = item.title, fontSize = 20.sp)
      Spacer(modifier = Modifier.weight(1f))
      Image(
          modifier = Modifier.size(30.dp),
          painter = painterResource(id = R.drawable.ic_next),
          contentDescription = "")
    }
    HorizontalDivider(color = colorResource(id = com.example.core.R.color.teal_200))
  }
}

@Composable
fun EmptyHistoryList() {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Text(text = stringResource(id = R.string.no_expense_records))
  }
}

@Composable
fun Toolbar(modifier: Modifier = Modifier, title: String, onBackClick: () -> Unit) {
  Row(
      modifier = modifier.fillMaxWidth().height(56.dp).padding(vertical = 10.dp),
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
