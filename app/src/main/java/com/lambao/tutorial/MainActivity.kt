package com.lambao.tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.lambao.tutorial.demo.Counter
import com.lambao.tutorial.demo.HandleBackPress
import com.lambao.tutorial.demo.LaunchedEffectDemo
import com.lambao.tutorial.demo.ProduceStateDemo
import com.lambao.tutorial.demo.RememberUpdatedStateDemo2
import com.lambao.tutorial.demo.SideEffectDemo
import com.lambao.tutorial.demo.SnapshotCounterDemo
import com.lambao.tutorial.demo.TimerScreen

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            demoLaunchedEffect()
//            demoRememberUpdatedState()
//            demoCoroutineScope()
//            demoDisposableEffect()
//            demoSideEffect()
//            demoProduceStateDemo()
//            demoDerivedState()
            demoSnapshotFlow()
        }
    }

    @Composable
    fun demoLaunchedEffect() {
        LaunchedEffectDemo()
    }

    @Composable
    fun demoRememberUpdatedState() {
        // DEMO 1
//    Demo1()

        // DEMO 2
        RememberUpdatedStateDemo2()

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
//    NetworkStatus()
        HandleBackPress(backPressedDispatcher = onBackPressedDispatcher)
    }

    @Composable
    fun demoSideEffect() {
        SideEffectDemo()
    }

    @Composable
    fun demoProduceStateDemo() {
        ProduceStateDemo()
    }

    @Composable
    fun demoDerivedState() {
//        DerivedStateDemo()
        Counter()
    }

    @Composable
    fun demoSnapshotFlow() {
        SnapshotCounterDemo()
    }
}

const val TAG = "lamnb"