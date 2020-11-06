package com.example.gram.screen.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import com.example.gram.screen.home.Home
import com.example.gram.ui.GramTheme
import com.example.gram.ui.bottomBarHeight
import com.example.gram.ui.icon

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                GramTheme {
                    val (currentSection, setCurrentSection) = savedInstanceState{ HomeSection.Home }
                    val navItems = HomeSection.values().toList()
                    Scaffold(
                        bottomBar = {
                            BottomBar(
                                items = navItems,
                                currentSelection = currentSection,
                                onSectionSelected = setCurrentSection
                            )
                        }
                    ) {innerPadding ->
                        val modifier = Modifier.padding(innerPadding)
                        Crossfade(modifier = modifier, current = currentSection) {section ->
                            when(section){
                                HomeSection.Home -> Home()
                                HomeSection.Search -> Content(title = "Search")
                                HomeSection.Add -> Content(title = "Add Post options")
                                HomeSection.Favorite -> Content(title = "Favorite")
                                HomeSection.Profile -> Content(title = "Profile")
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
private fun BottomBar(
    items: List<HomeSection>,
    currentSelection: HomeSection,
    onSectionSelected: (HomeSection) -> Unit
) {
    BottomNavigation(
        modifier = Modifier.height(bottomBarHeight),
        contentColor = contentColor(),
        backgroundColor = MaterialTheme.colors.background
    ) {
        items.forEach { section ->
            val selected = section == currentSelection

            val iconRes = if (selected) section.selectedIcon else section.icon

            BottomNavigationItem(
                icon = {
                    Icon(imageResource(id = iconRes), modifier = Modifier.icon())
                },
                selected = selected,
                onSelect = { onSectionSelected(section) },
                alwaysShowLabels = false
            )
        }
    }
}

@Composable
private fun Content(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        gravity = ContentGravity.Center
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )
    }
}

private enum class HomeSection(
    val icon: Int,
    val selectedIcon: Int
) {
    Home(com.example.gram.R.drawable.ic_outlined_home, com.example.gram.R.drawable.ic_filled_home),
    Search(com.example.gram.R.drawable.ic_outlined_search, com.example.gram.R.drawable.ic_outlined_search),
    Add(com.example.gram.R.drawable.ic_outlined_add, com.example.gram.R.drawable.ic_outlined_add),
    Favorite(com.example.gram.R.drawable.ic_outlined_favorite, com.example.gram.R.drawable.ic_filled_favorite),
    Profile(com.example.gram.R.drawable.ic_outlined_reels, com.example.gram.R.drawable.ic_outlined_reels)
}