package com.example.cost_accounting_impl.presentation.compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.cost_accounting.R
import com.example.cost_accounting_impl.presentation.CostAccountingViewModel
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePicker(viewModel: CostAccountingViewModel) {
  val date by viewModel.localDate.collectAsState()

  val isOpen = remember { mutableStateOf(false) }

  val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

  Row(verticalAlignment = Alignment.CenterVertically) {
    OutlinedTextField(
        readOnly = true,
        value = date.format(dateFormatter),
        label = { Text(text = stringResource(R.string.choose_date)) },
        onValueChange = {})

    IconButton(onClick = { isOpen.value = true }) {
      Image(painterResource(R.drawable.ic_calendar), contentDescription = "Calendar")
    }
  }

  if (isOpen.value) {
    CustomDatePickerDialog(
        onAccept = {
          isOpen.value = false

          if (it != null) {
            viewModel.changeDate(it)
          }
        },
        onCancel = { isOpen.value = false })
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerDialog(onAccept: (Long?) -> Unit, onCancel: () -> Unit) {
  val state = rememberDatePickerState()

  DatePickerDialog(
      onDismissRequest = {},
      confirmButton = {
        Button(onClick = { onAccept(state.selectedDateMillis) }) {
          Text(text = stringResource(id = R.string.ok))
        }
      },
      dismissButton = {
        Button(onClick = onCancel) { Text(text = stringResource(id = R.string.cancel)) }
      }) {
        androidx.compose.material3.DatePicker(state = state)
      }
}
