package com.example.main.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.core.SpenderInTravelTheme
import com.example.core_api.mediator.AppWithFacade
import com.example.main.di.MenuComponent
import javax.inject.Inject

class MenuFragment : Fragment() {

  @Inject lateinit var vmFactory: MenuViewModelFactory
  lateinit var viewModel: MenuViewModel

  private val menuComponent: MenuComponent by lazy {
    MenuComponent.create((requireActivity().application as AppWithFacade).getFacade())
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    menuComponent.inject(this)
    vmFactory = menuComponent.getViewModelFactory()
    viewModel = ViewModelProvider(this, vmFactory)[MenuViewModel::class.java]
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      fitsSystemWindows = true
      setContent { SpenderInTravelTheme { MenuScreen(onMenuClick = viewModel::onMenuClick) } }
    }
  }
}
