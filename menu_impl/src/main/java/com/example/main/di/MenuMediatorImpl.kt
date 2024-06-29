package com.example.main.di

import com.example.main.presentation.MenuFragment
import com.example.main_api.MenuMediator
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class MenuMediatorImpl @Inject constructor() : MenuMediator {

    override fun getMenuScreen(): Screen {
        return FragmentScreen { MenuFragment() }
    }
}
