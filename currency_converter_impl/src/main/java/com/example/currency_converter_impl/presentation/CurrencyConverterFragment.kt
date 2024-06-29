package com.example.currency_converter_impl.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.core_api.mediator.AppWithFacade
import com.example.currency_converter_impl.di.CurrencyConverterComponent
import javax.inject.Inject

class CurrencyConverterFragment : Fragment() {

  @Inject lateinit var vmFactory: CurrencyConverterViewModelFactory
  lateinit var viewModel: CurrencyConverterViewModel

  private val currencyConverterComponent: CurrencyConverterComponent by lazy {
    CurrencyConverterComponent.create((requireActivity().application as AppWithFacade).getFacade())
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    currencyConverterComponent.inject(this)
    vmFactory = currencyConverterComponent.getViewModelFactory()
    viewModel = ViewModelProvider(this, vmFactory)[CurrencyConverterViewModel::class.java]
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent { CurrencyConverterScreen(viewModel = viewModel) }
    }
  }
}
