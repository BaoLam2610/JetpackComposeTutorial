package com.lambao.tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.lambao.tutorial.demo.Demo1
import com.lambao.tutorial.demo.KeyboardAction
import com.lambao.tutorial.demo.LaunchedEffectDemo
import com.lambao.tutorial.demo.NetworkStatus
import com.lambao.tutorial.demo.TimerScreen

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            demoLaunchedEffect()
//            demoRememberUpdatedState()
//            demoCoroutineScope()
            demoDisposableEffect()
        }
    }


}

@Composable
fun demoLaunchedEffect() {
    LaunchedEffectDemo()
}

@Composable
fun demoRememberUpdatedState() {
    // DEMO 1
    Demo1()

    // DEMO 2
//    RememberUpdatedStateDemo2()

    // DEMO 3
//    ShowToast(useRememberUpdatedState = true, message = {
//        "Lam la tui"
//    })
}

@Composable
fun demoCoroutineScope() {
    TimerScreen()
}

@Composable
fun demoDisposableEffect() {
//    PlayMusic()
//    KeyboardAction()
    NetworkStatus()
}

const val TAG = "lamnb"