package com.example.gram.screen.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.findNavController
import com.example.gram.R
import com.example.gram.screen.home.Home
import com.example.gram.ui.GramTheme

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                GramTheme {
                    Home()
                }
            }
        }
    }

}