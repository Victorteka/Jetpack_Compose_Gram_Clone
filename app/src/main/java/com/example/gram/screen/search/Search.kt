package com.example.gram.screen.search


import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.gram.R
import com.example.gram.ui.icon

@Composable
fun Search() {
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize().padding(vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row() {
                    Icon(
                        vectorResource(id = R.drawable.ic_baseline_search_24),
                        modifier = Modifier.icon()
                    )
                    Text(text = "Search", modifier = Modifier.padding(horizontal = 5.dp))
                }
                Icon(vectorResource(id = R.drawable.ic_qr_scanner), modifier = Modifier.icon())
            }
            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Reels()
        }
    }
}