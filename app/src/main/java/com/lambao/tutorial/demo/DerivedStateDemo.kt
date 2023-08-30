package com.lambao.tutorial.demo

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.lambao.tutorial.TAG

/* DEMO 1 */
@Composable
fun DerivedStateDemo() {
    var counter by remember {
        mutableStateOf(0)
    }
    // WRONG
//    val counterText = "Current counter is " + counter
    // CORRECT
    val counterText by remember {
        derivedStateOf {
            "Current counter is $counter"
        }
    }
//    val counterText by derivedStateOf {
//        "Current counter is $counter"
//    }

    Column {
        Button(onClick = { counter++ }) {
            Text(text = "Add")
        }
        Text(text = counterText)
    }
}


/* DEMO 2 */
@Composable
fun Counter() {
    var counterState by remember { mutableStateOf(0) }

    val showHurrayState by remember {
        derivedStateOf {
            counterState > 10
        }
    }

    Button(
        onClick = { counterState++ }
    ) {
        Text(counterState.toString())
    }

    if (showHurrayState) Text("Hurray!")
}