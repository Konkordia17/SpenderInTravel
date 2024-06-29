package com.example.history_impl.presentation.costs_per_day

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.history_impl.domain.HistoryInteractor
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class CostsPerDayViewModelFactory
@Inject
constructor(private val historyInteractor: HistoryInteractor, private val router: Router) :
    ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return when (modelClass) {
      CostsPerDayViewModel::class.java -> CostsPerDayViewModel(historyInteractor, router)
      else -> throw IllegalArgumentException("Unknown ViewModel class")
    }
        as T
  }
}
