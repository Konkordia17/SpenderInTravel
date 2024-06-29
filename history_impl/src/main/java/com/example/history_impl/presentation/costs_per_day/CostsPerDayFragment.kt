package com.example.history_impl.presentation.costs_per_day

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.core_api.mediator.AppWithFacade
import com.example.history_impl.di.HistoryComponent
import com.example.history_impl.presentation.models.GroupedCostsUiModel
import javax.inject.Inject

class CostsPerDayFragment : Fragment() {

  @Inject lateinit var vmFactory: CostsPerDayViewModelFactory
  lateinit var viewModel: CostsPerDayViewModel

  private val costs: GroupedCostsUiModel? by lazy { requireArguments().getParcelable(COSTS) }

  private val historyComponent: HistoryComponent by lazy {
    HistoryComponent.create((requireActivity().application as AppWithFacade).getFacade())
  }

  @RequiresApi(Build.VERSION_CODES.O)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    historyComponent.inject(this)
    vmFactory = historyComponent.getCostsViewModelFactory()
    viewModel = ViewModelProvider(this, vmFactory)[CostsPerDayViewModel::class.java]
  }

  @RequiresApi(Build.VERSION_CODES.O)
  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        CostsPerDayScreen(
            viewModel = viewModel,
            groupedCosts = costs,
            onCurrencyChange = viewModel::updateCurrency)
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    if (!costs?.costs.isNullOrEmpty()) {
      viewModel.getHistoricalCurrencies(
          date = costs?.costs?.firstOrNull()?.date!!, groupedCostsUiModel = costs!!)
    }
  }

  companion object {

    private const val COSTS = "costs"

    fun newInstance(costs: GroupedCostsUiModel): CostsPerDayFragment =
        CostsPerDayFragment().apply { arguments = bundleOf(COSTS to costs) }
  }
}
