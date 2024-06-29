package com.example.cost_accounting_impl.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cost_accounting.R
import com.example.cost_accounting_impl.presentation.models.CostItemUiModel
import kotlinx.collections.immutable.ImmutableList

@Composable
fun CostsList(
    modifier: Modifier,
    items: ImmutableList<CostItemUiModel>,
    doOnDelete: (Int) -> Unit
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        itemsIndexed(items = items) { index, item ->
            CostItem(id = index + 1, item = item, doOnDelete = { doOnDelete.invoke(index) })
        }
    }
}

@Composable
fun CostItem(id: Int, item: CostItemUiModel, doOnDelete: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Text(text = "$id.", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text(modifier = Modifier.padding(start = 8.dp), text = item.category, fontSize = 20.sp)
        Text(
            modifier = Modifier.padding(start = 8.dp), text = item.sum.toString(), fontSize = 20.sp)
        Text(modifier = Modifier.padding(start = 8.dp), text = item.currency, fontSize = 20.sp)
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = "",
            modifier = Modifier.size(30.dp).clickable { doOnDelete.invoke() })
    }
}