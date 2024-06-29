package com.example.history_impl.presentation.history_list

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.core_api.mediator.AppWithFacade
import com.example.history_impl.di.HistoryComponent
import javax.inject.Inject

class HistoryFragment : Fragment() {
  @Inject lateinit var vmFactory: HistoryViewModelFactory
  lateinit var viewModel: HistoryViewModel

  private val historyComponent: HistoryComponent by lazy {
    HistoryComponent.create((requireActivity().application as AppWithFacade).getFacade())
  }

  @RequiresApi(Build.VERSION_CODES.O)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    historyComponent.inject(this)
    vmFactory = historyComponent.getViewModelFactory()
    viewModel = ViewModelProvider(this, vmFactory)[HistoryViewModel::class.java]
  }

  @RequiresApi(Build.VERSION_CODES.O)
  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply { setContent { HistoryScreen(viewModel) } }
  }
}
