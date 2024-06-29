package com.example.cost_accounting_impl.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cost_accounting.R
import com.example.cost_accounting_impl.domain.CostAccountingInteractor
import com.example.cost_accounting_impl.presentation.models.CategoryItem
import com.example.cost_accounting_impl.presentation.models.CostAccountingScreenState
import com.example.cost_accounting_impl.presentation.models.CostItemUiModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import javax.inject.Inject
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CostAccountingViewModel
@Inject
constructor(private val costAccountingInteractor: CostAccountingInteractor) : ViewModel() {

  private val _costsList = MutableStateFlow<ImmutableList<CostItemUiModel>>(persistentListOf())
  val costsList: StateFlow<ImmutableList<CostItemUiModel>> = _costsList

  private val _costsState =
      MutableStateFlow<CostAccountingScreenState>(CostAccountingScreenState.Initial)
  val costsState: StateFlow<CostAccountingScreenState> = _costsState

  val isAlertShown = MutableStateFlow<Boolean>(false)

  @RequiresApi(Build.VERSION_CODES.O) private val _localDate = MutableStateFlow(LocalDate.now())
  @RequiresApi(Build.VERSION_CODES.O) val localDate: StateFlow<LocalDate> = _localDate

  private val categoriesList =
      MutableStateFlow<ImmutableList<CategoryItem>>(
          persistentListOf(
              CategoryItem(name = "Здоровье", imageRes = R.drawable.ic_health),
              CategoryItem(name = "Еда", imageRes = R.drawable.ic_food),
              CategoryItem(name = "Развлечения", imageRes = R.drawable.ic_entertainment),
              CategoryItem(name = "Транспорт", imageRes = R.drawable.ic_transport),
              CategoryItem(name = "Путешествия", imageRes = R.drawable.ic_travel),
              CategoryItem(name = "Дом", imageRes = R.drawable.ic_spends),
              CategoryItem(name = "Одежда", imageRes = R.drawable.ic_clothes),
              CategoryItem(name = "Другое", imageRes = R.drawable.ic_money),
          ))

  @RequiresApi(Build.VERSION_CODES.O)
  fun addCost(category: String, comments: String, currency: String, sum: Double?) {
    _costsList.value =
        (_costsList.value +
                CostItemUiModel(
                    category = category,
                    comments = comments,
                    currency = currency,
                    sum = sum ?: 0.0,
                    date =
                        _localDate.value.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()))
            .toImmutableList()
  }

  fun deleteCost(id: Int) {
    _costsList.value = _costsList.value.toMutableList().apply { removeAt(id) }.toImmutableList()
  }

  fun getCategories(): ImmutableList<CategoryItem> {
    return categoriesList.value
  }

  fun saveCosts() {
    viewModelScope.launch {
      _costsState.value = CostAccountingScreenState.Loading
      try {
        costAccountingInteractor.saveCosts(_costsList.value)
        _costsState.value = CostAccountingScreenState.Initial
        _costsList.value = persistentListOf()
        isAlertShown.value = true
      } catch (t: Throwable) {
        _costsState.value = CostAccountingScreenState.Error
      }
    }
  }

  @RequiresApi(Build.VERSION_CODES.O)
  fun changeDate(date: Long) {
    _localDate.value = Instant.ofEpochMilli(date).atZone(ZoneId.of("UTC")).toLocalDate()
  }

    fun closeAlert() {
        isAlertShown.value = false
    }
}
