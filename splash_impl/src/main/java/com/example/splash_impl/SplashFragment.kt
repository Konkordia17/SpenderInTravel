package com.example.splash_impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.core.SpenderInTravelTheme
import com.example.core_api.mediator.AppWithFacade
import com.example.main_api.MenuMediator
import com.example.splash_impl.di.SplashComponent
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class SplashFragment : Fragment() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var menuMediator: MenuMediator

    private val splashComponent: SplashComponent by lazy {
        SplashComponent.create((requireActivity().application as AppWithFacade).getFacade())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            fitsSystemWindows = true
            setContent { SpenderInTravelTheme { SplashScreen { openMenuScreen() } } }
        }
    }

    private fun openMenuScreen() {
        router.replaceScreen(menuMediator.getMenuScreen())
    }
}
