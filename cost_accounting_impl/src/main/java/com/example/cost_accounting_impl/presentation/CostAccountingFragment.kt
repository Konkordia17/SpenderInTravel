package com.example.cost_accounting_impl.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.core.SpenderInTravelTheme
import com.example.core_api.mediator.AppWithFacade
import com.example.cost_accounting_impl.di.CostAccountingComponent
import com.example.cost_accounting_impl.presentation.compose.CostAccountingScreen
import javax.inject.Inject

class CostAccountingFragment : Fragment() {

  @Inject lateinit var vmFactory: CostAccountingViewModelFactory
  lateinit var viewModel: CostAccountingViewModel

  private val costAccountingComponent: CostAccountingComponent by lazy {
    CostAccountingComponent.create((requireActivity().application as AppWithFacade).getFacade())
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    costAccountingComponent.inject(this)
    vmFactory = costAccountingComponent.getViewModelFactory()
    viewModel = ViewModelProvider(this, vmFactory)[CostAccountingViewModel::class.java]
  }

  @RequiresApi(Build.VERSION_CODES.O)
  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      fitsSystemWindows = true
      setContent {
        SpenderInTravelTheme {
          CostAccountingScreen(
              viewModel = viewModel,
              optionText = getString(com.example.core_api.R.string.choose_currency))
        }
      }
    }
  }
}
