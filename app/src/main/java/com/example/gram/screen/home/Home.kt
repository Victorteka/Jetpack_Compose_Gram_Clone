package com.example.gram.screen.home

import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.gram.R
import com.example.gram.ui.icon


@Composable
fun Home() {
    Scaffold(topBar = { Toolbar() }) {
        
    }
}

@Composable
private fun Toolbar() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalGravity = Alignment.CenterVertically
        ) {
            Icon(
                vectorResource(id = R.drawable.ic_camera),
                modifier = Modifier.icon()
            )
            Box(
                modifier = Modifier.padding(12.dp),
                gravity = ContentGravity.Center
            ) {
                Icon(vectorResource(id = R.drawable.ic_instagram))
            }
            Icon(imageResource(id = R.drawable.ic_dm), modifier = Modifier.icon())
        }
    }
}