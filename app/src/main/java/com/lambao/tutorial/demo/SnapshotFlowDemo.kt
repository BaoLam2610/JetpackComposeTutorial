package com.lambao.tutorial.demo

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import com.lambao.tutorial.TAG
import kotlinx.coroutines.flow.map

/* DEMO 1 */
@Composable
fun SnapshotCounterDemo() {
    var count by remember { mutableStateOf(0) }

    val countFlow = snapshotFlow { count }

    LaunchedEffect(countFlow) {
        countFlow
            .map { it * 2 }
            .collect { value ->
            // Handle the new value
            Log.d(TAG, "value: $value")
        }
    }

    Button(onClick = { count++ }) {
        Text("Clicked $count times")
    }
}