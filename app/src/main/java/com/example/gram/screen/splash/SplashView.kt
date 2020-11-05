package com.example.gram.screen.splash

import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.launchInComposition
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import com.example.gram.R
import kotlinx.coroutines.delay


private const val DELAY: Long = 3000

@Composable
fun SplashView(modifier: Modifier = Modifier, loadNextScreen: () -> Unit) {
    Surface {
        Box(modifier = modifier.fillMaxSize(), gravity = ContentGravity.Center) {
            launchInComposition {
                delay(DELAY)
                loadNextScreen()
            }
            Image(vectorResource(id = R.drawable.ic_instagram))
            Text(text = "Made by yours truly Victor Teka")
        }
    }
}