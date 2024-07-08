package com.example.cost_accounting_impl.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.core.R

@Composable
fun InputDialog(onDismiss: () -> Unit, onAccept: (sum: Double?, comments: String) -> Unit) {
  var sum by remember { mutableStateOf("") }
  var comments by remember { mutableStateOf("") }
  var isErrorShown by remember { mutableStateOf(false) }

  Dialog(
      onDismissRequest = { onDismiss() },
      properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth().padding(16.dp).testTag("InputDialog")) {
              Column(
                  modifier = Modifier.padding(16.dp),
                  verticalArrangement = Arrangement.Center,
                  horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text =
                            stringResource(id = com.example.cost_accounting.R.string.mentioned_sum))
                    Spacer(modifier = Modifier.height(8.dp))
                    if (isErrorShown) {
                      Text(
                          modifier = Modifier.testTag("input_error"),
                          text =
                              stringResource(
                                  id = com.example.cost_accounting.R.string.complete_field),
                          color = colorResource(id = R.color.red_600))
                    }
                    TextField(
                        value = sum,
                        onValueChange = {
                          if (it.isEmpty() || it.toDoubleOrNull() != null) {
                            sum = it
                          }
                        },
                        modifier = Modifier.fillMaxWidth().testTag("costs"),
                        placeholder = {
                          Text(
                              stringResource(
                                  id = com.example.cost_accounting.R.string.sum_placeholder))
                        },
                        keyboardOptions =
                            KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done, keyboardType = KeyboardType.Decimal))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Комментарий")
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = comments,
                        onValueChange = { comments = it },
                        modifier = Modifier.fillMaxWidth().testTag("comments"),
                        placeholder = {
                          Text(
                              stringResource(
                                  id =
                                      com.example.cost_accounting.R.string
                                          .add_description_placeholder))
                        },
                        keyboardOptions =
                            KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done, keyboardType = KeyboardType.Text))
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()) {
                          TextButton(onClick = { onDismiss() }) {
                            Text(stringResource(id = com.example.cost_accounting.R.string.cancel))
                          }
                          Spacer(modifier = Modifier.width(8.dp))
                          TextButton(
                              onClick = {
                                if (sum.isEmpty()) {
                                  isErrorShown = true
                                } else {
                                  onAccept.invoke(sum.toDoubleOrNull(), comments)
                                  onDismiss()
                                }
                              }) {
                                Text(text = stringResource(id = com.example.cost_accounting.R.string.ok), modifier = Modifier.testTag("alert_confirm_button"))
                              }
                        }
                  }
            }
      }
}
