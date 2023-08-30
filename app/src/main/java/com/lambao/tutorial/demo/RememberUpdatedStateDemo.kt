package com.lambao.tutorial.demo

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.lambao.tutorial.TAG
import kotlinx.coroutines.delay


/* DEMO 1*/
@Composable
fun Demo1() {
    var state by remember {
        mutableStateOf(::colorGreen)
    }
    Column {
        Button(onClick = {
            state = ::colorRed
        }) {
            Text(text = "Changed button color")
        }

        RememberUpdatedStateDemo(state)
    }
}

@Composable
fun RememberUpdatedStateDemo(
    onTimeout: () -> Unit
) {
    val rememberOnTimeout by rememberUpdatedState(newValue = onTimeout)
    LaunchedEffect(key1 = true) {
        delay(8000)
        rememberOnTimeout()
//        onTimeout()
    }
}

fun colorGreen() {
    Log.d(TAG, "colorGreen")
}

fun colorRed() {
    Log.d(TAG, "colorRed")
}

/* DEMO 2 */
@Composable
fun Calculation(input: Int) {
    val rememberUpdatedStateInput by rememberUpdatedState(input)
    val rememberedInput = remember { input }

    Text("updatedInput: $rememberUpdatedStateInput, rememberedInput: $rememberedInput")
}

@Composable
fun RememberUpdatedStateDemo2() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var myInput by remember {
            mutableStateOf(0)
        }

        OutlinedButton(
            onClick = {
                myInput++
            }
        ) {
            Text("Increase $myInput")
        }
        Calculation(input = myInput)
    }
}

/* DEMO 3*/
@Composable
fun ShowToast(
    useRememberUpdatedState: Boolean,
    message: (() -> String)? = null
) {
    val context = LocalContext.current
    var actualTrueMessage: State<(() -> String)?>? = null
    if (useRememberUpdatedState) {
        actualTrueMessage = rememberUpdatedState(message)
    }
    LaunchedEffect(Unit) {
        delay(5000)
        Toast.makeText(
            context,
            actualTrueMessage?.value?.invoke() ?: message?.invoke(),
            Toast.LENGTH_SHORT
        ).show()
    }
}