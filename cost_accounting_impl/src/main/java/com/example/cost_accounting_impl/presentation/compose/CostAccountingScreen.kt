package com.example.cost_accounting_impl.presentation.compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.cost_accounting.R
import com.example.cost_accounting_impl.presentation.CostAccountingViewModel
import com.example.cost_accounting_impl.presentation.models.CategoryItem
import com.example.cost_accounting_impl.presentation.models.CostAccountingScreenState
import com.example.cost_accounting_impl.presentation.models.CostItemUiModel
import kotlinx.collections.immutable.ImmutableList

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CostAccountingScreen(viewModel: CostAccountingViewModel, optionText: String) {
  var expanded by remember { mutableStateOf(false) }
  var selectedOptionText by remember { mutableStateOf(optionText) }
  var selectedCurrency by remember { mutableStateOf<Currencies?>(null) }
  val options = Currencies.entries.toTypedArray()
  var chosenCategory by remember { mutableStateOf<CategoryItem?>(null) }
  val costs by viewModel.costsList.collectAsState()
  var isCurrencyError by remember { mutableStateOf(false) }
  val isCostsSaved by viewModel.isAlertShown.collectAsState()
  Column(
      modifier =
          Modifier.fillMaxSize()
              .background(color = colorResource(id = com.example.core.R.color.brand_color))
              .padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally) {
        if (isCostsSaved) {
          CostsSuccessAlert { viewModel.closeAlert() }
        }
        DatePicker(viewModel)

        Row(
            Modifier.padding(top = 30.dp).fillMaxWidth().clickable { expanded = true },
            verticalAlignment = Alignment.CenterVertically) {
              Text(
                  textAlign = TextAlign.Center,
                  text = selectedOptionText,
                  modifier =
                      Modifier.border(
                              width = 1.dp,
                              color = colorResource(id = com.example.core.R.color.teal_200),
                              shape = RoundedCornerShape(10.dp))
                          .padding(5.dp))
              Image(painterResource(id = R.drawable.ic_select), contentDescription = "")
              if (isCurrencyError) {
                Text(
                    text = stringResource(id = R.string.choose_currency_error),
                    color = colorResource(id = com.example.core.R.color.red_600))
              }

              DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach { option ->
                  DropdownMenuItem(
                      onClick = {
                        selectedOptionText = option.description
                        selectedCurrency = option
                        expanded = false
                        isCurrencyError = false
                      },
                      text = { Text(text = option.description) })
                }
              }
            }
        Categories(itemsList = viewModel.getCategories()) { categoryItem ->
          if (selectedCurrency != null) {
            chosenCategory = categoryItem
          } else {
            isCurrencyError = true
          }
        }

        chosenCategory?.let { category ->
          InputDialog(
              onDismiss = { chosenCategory = null },
              onAccept = { sum, comments ->
                viewModel.addCost(
                    sum = sum,
                    comments = comments,
                    currency = selectedCurrency?.name.orEmpty(),
                    category = category.name)
              })
        }
        CostsBlock(modifier = Modifier.weight(1f), viewModel = viewModel, costs = costs)
      }
}

@Composable
fun CostsBlock(
    modifier: Modifier,
    viewModel: CostAccountingViewModel,
    costs: ImmutableList<CostItemUiModel>
) {
  val costsState by viewModel.costsState.collectAsState()
  when (costsState) {
    CostAccountingScreenState.Loading -> {
      Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(40.dp))
      }
    }
    CostAccountingScreenState.Initial -> {
      CostsList(modifier = modifier, items = costs, doOnDelete = viewModel::deleteCost)
      CostAccountingButton(isEnabled = costs.isNotEmpty(), onClick = viewModel::saveCosts)
    }
    CostAccountingScreenState.Error -> {}
  }
}

@Composable
fun CostsSuccessAlert(onDismiss: () -> Unit) {
  AlertDialog(
      onDismissRequest = { onDismiss.invoke() },
      confirmButton = {},
      text = { Text(text = stringResource(id = R.string.notes_are_saved)) },
      dismissButton = {
        Text(
            text = stringResource(id = R.string.ok),
            modifier = Modifier.clickable { onDismiss.invoke() })
      })
}

@Composable
fun CostAccountingButton(isEnabled: Boolean, onClick: () -> Unit) {

  OutlinedButton(
      onClick = onClick,
      enabled = isEnabled,
      modifier = Modifier.padding(top = 16.dp).fillMaxWidth().height(56.dp),
      shape = RoundedCornerShape(10.dp),
      border =
          BorderStroke(
              1.dp,
              colorResource(
                  id =
                      if (isEnabled) {
                        com.example.core.R.color.teal_700
                      } else {
                        com.example.core.R.color.transparent
                      })),
      colors =
          ButtonDefaults.buttonColors(
              containerColor = colorResource(id = com.example.core.R.color.teal_700),
              disabledContentColor = colorResource(id = com.example.core.R.color.gray))) {
        Text(
            text = stringResource(id = R.string.save),
            fontSize = 18.sp,
            color = colorResource(id = com.example.core.R.color.white),
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 12.dp))
      }
}
