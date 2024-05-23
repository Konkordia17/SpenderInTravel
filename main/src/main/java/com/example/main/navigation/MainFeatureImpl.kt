package com.example.main.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.core_api.navigation.NavigationItem
import com.example.main.presentation.MenuScreen
import com.example.main.presentation.SplashScreen
import com.example.main_api.MainFeatureApi
import javax.inject.Inject

class MainFeatureImpl @Inject constructor() : MainFeatureApi {

  override val homeRoute = NavigationItem.SplashScreen.router

  override fun registerGraph(
      navGraphBuilder: NavGraphBuilder,
      navController: NavHostController,
      modifier: Modifier,
  ) {

    navGraphBuilder.composable(homeRoute) {
      SplashScreen {
        navController.navigate(NavigationItem.MenuScreen.router) {
          popUpTo(NavigationItem.SplashScreen.router) { inclusive = true }
        }
      }
    }

    navGraphBuilder.composable(NavigationItem.MenuScreen.router) { MenuScreen(navController) }
  }
}
