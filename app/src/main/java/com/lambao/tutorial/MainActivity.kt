package com.lambao.tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lambao.tutorial.ui.home.HomeScreen
import com.lambao.tutorial.ui.theme.MeditationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditationTheme {
                HomeScreen()
            }
        }
    }
}