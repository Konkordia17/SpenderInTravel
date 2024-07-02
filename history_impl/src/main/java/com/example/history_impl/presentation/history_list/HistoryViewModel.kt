package com.example.history_impl.presentation.history_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_api.database.models.CostDbItem
import com.example.history_impl.domain.HistoryInteractor
import com.example.history_impl.presentation.costs_per_day.CostsPerDayFragment
import com.example.history_impl.presentation.mappers.CostItemMapper
import com.example.history_impl.presentation.models.CostItemUiModel
import com.example.history_impl.presentation.models.GroupedCostsUiModel
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class HistoryViewModel
@Inject
constructor(
    private val historyInteractor: HistoryInteractor,
    private val router: Router
) : ViewModel() {

  private val _historyScreenState = MutableStateFlow<HistoryScreenState>(HistoryScreenState.Loading)
  val historyScreenState: StateFlow<HistoryScreenState> = _historyScreenState

  init {
    getCosts()
  }

  fun openCostsPerDay(costs: GroupedCostsUiModel) {
    router.navigateTo(FragmentScreen { CostsPerDayFragment.newInstance(costs) })
  }

  fun back() {
    router.exit()
  }

  @RequiresApi(Build.VERSION_CODES.O)
  private fun getCosts() {
    viewModelScope.launch {
      try {
        val result = historyInteractor.getCosts()
        _historyScreenState.value = HistoryScreenState.HistoryList(groupCostsByDate(result))
      } catch (t: Throwable) {
        println()
      }
    }
  }

  @RequiresApi(Build.VERSION_CODES.O)
  private fun groupCostsByDate(costs: List<CostDbItem>): ImmutableList<GroupedCostsUiModel> {
    val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy год", Locale("ru"))

    return costs
        .groupBy { it.date }
        .toSortedMap(compareByDescending { it })
        .map { entry ->
          val date =
              LocalDateTime.ofInstant(Instant.ofEpochMilli(entry.key), ZoneId.systemDefault())
          val groupedItems =
              entry.value
                  .groupBy { it.category to it.currency }
                  .map { (categoryCurrency, items) ->
                    val (category, currency) = categoryCurrency
                    val combinedComments =
                        items.map { it.comments }.filter { it.isNotEmpty() }.joinToString(", ")
                    CostItemUiModel(
                        category = category,
                        currency = currency,
                        sum = items.sumOf { it.sum },
                        comments = combinedComments,
                        date = entry.key)
                  }
                  .toImmutableList()

          val totalSum = groupedItems.sumOf { it.sum }
          GroupedCostsUiModel(
              title = date.format(formatter), costs = groupedItems, totalSum = totalSum)
        }
        .toImmutableList()
  }
}
