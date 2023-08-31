package com.lambao.tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = rememberLazyGridState(initialFirstVisibleItemIndex = 99)
            LazyVerticalGrid(columns = GridCells.Adaptive(100.dp)  /* or Fixed(5) */,
                state = state) {
                items(100) {
                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(
                                Color.LightGray
                            )
                    ) {
                        Text(text = "Item $it", color = Color.Black)
                    }
                }
            }
        }
    }
}