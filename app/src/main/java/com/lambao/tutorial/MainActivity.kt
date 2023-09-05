package com.lambao.tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel = viewModel<MainViewModel>()

            val changeColor = remember<(Color) -> Unit> {
                {
                    viewModel.changeColor(it)
                }
            }

            RgbSelector(
                color = viewModel.color,
                onColorClick = viewModel::changeColor/*changeColor*/ ,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

@Composable
fun RgbSelector(
    color: Color,
    onColorClick: (Color) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color)
        )
        
        Text(text = "Color: $color")

        Button(onClick = { onColorClick(Color.Red) }) {
            Text(text = "Red")
            key() {

            }
        }

        Button(onClick = { onColorClick(Color.Green) }) {
            Text(text = "Green")
        }

        Button(onClick = { onColorClick(Color.Blue) }) {
            Text(text = "Blue")
        }
    }
}