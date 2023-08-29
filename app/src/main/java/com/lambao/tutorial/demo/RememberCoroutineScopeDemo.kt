package com.lambao.tutorial.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun TimerScreen() {
    /* coroutine này chỉ hoạt động trong phạm vi của hàm composable này
    * nếu hàm này cook thì scope này cook
    * */
    val scope = rememberCoroutineScope()

    val job: Job? by remember {
        mutableStateOf(null)
    }

    Column {
        Button(onClick = {
            println("Timer started")
            scope.launch {
                try {
                    delay(5000)
                    println("Timer ended")
                } catch (ex: Exception) {
                    println("Timer cancelled")
                }
            }
        }) {
            Text("Start Timer")
        }

        Spacer(Modifier.height(20.dp))

        Button(onClick = {
            println("Cancelling timer")
            // cancel job
            job?.cancel()

            // cancel scope
//            scope.cancel()
        }) {
            Text("Cancel Timer")
        }
    }
}