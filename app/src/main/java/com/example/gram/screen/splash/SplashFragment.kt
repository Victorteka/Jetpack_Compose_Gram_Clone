package com.example.gram.screen.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.findNavController
import com.example.gram.R
import com.example.gram.ui.GramTheme

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                GramTheme {
                    SplashView{
                        findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
                    }
                }
            }
        }
    }

}