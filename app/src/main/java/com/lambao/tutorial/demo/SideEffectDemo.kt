package com.lambao.tutorial.demo

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.lambao.tutorial.TAG


/* DEMO 1 */
var count = 0

@Composable
fun SideEffectDemo() {
    var index by remember {
        mutableStateOf(0)
    }

    // Call once time
    SideEffect {
        count++
        Log.d(TAG, "current count: $count")
    }
    Button(onClick = { index++ }) {
        Text(text = "Add")
    }
}