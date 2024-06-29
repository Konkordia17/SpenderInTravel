package com.example.history_impl.di

import com.example.history_api.di.HistoryMediator
import com.example.history_impl.presentation.history_list.HistoryFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class HistoryMediatorImpl @Inject constructor() : HistoryMediator {

  override fun getHistoryMediatorScreen(): Screen {
    return FragmentScreen { HistoryFragment() }
  }
}
