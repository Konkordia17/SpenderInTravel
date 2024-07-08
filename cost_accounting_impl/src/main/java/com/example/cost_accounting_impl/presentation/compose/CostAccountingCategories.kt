package com.example.cost_accounting_impl.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.cost_accounting_impl.presentation.models.CategoryItem
import kotlinx.collections.immutable.ImmutableList

@Composable
fun Categories(itemsList: ImmutableList<CategoryItem>, onOpenDialog: (CategoryItem) -> Unit) {
  LazyVerticalGrid(
      columns = GridCells.Fixed(3),
      modifier = Modifier.testTag("Categories").padding(horizontal = 2.dp, vertical = 20.dp)) {
        items(itemsList) { item -> GridItem(item, onOpenDialog) }
      }
}

@Composable
fun GridItem(item: CategoryItem, onOpenDialog: (CategoryItem) -> Unit) {
  Card(
      modifier =
          Modifier.padding(4.dp)
              .clickable { onOpenDialog.invoke(item) }
              .semantics { contentDescription = item.name },
      elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
              Image(
                  modifier = Modifier.height(60.dp),
                  painter = painterResource(id = item.imageRes),
                  contentDescription = "")
              Text(text = item.name)
            }
      }
}
