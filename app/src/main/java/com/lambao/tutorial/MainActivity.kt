package com.lambao.tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.lambao.tutorial.ui.theme.TutorialTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutorialTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Text(
                        text = "Hello World",
                        modifier = Modifier
                            .background(Color.Red)
                            .padding(MaterialTheme.spacing.extraLarge)
                    )
                }
            }
        }
    }
}