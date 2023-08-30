package com.lambao.tutorial.demo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.util.Log
import android.view.ViewTreeObserver
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lambao.tutorial.R
import com.lambao.tutorial.TAG

/* DEMO 1 */
@Composable
fun PlayMusic() {
    val context = LocalContext.current
    DisposableEffect(key1 = Unit, effect = {
        val mediaPlayer = MediaPlayer.create(context, R.raw.music)
        mediaPlayer.start()

        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    })
}

/* DEMO 2 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeyboardAction() {
    val view = LocalView.current
    DisposableEffect(key1 = Unit) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val insets = ViewCompat.getRootWindowInsets(view)
            val isKeyboardVisible = insets?.isVisible(WindowInsetsCompat.Type.ime())
            Log.d(TAG, "KeyboardAction: $isKeyboardVisible")
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
    TextField(value = "", onValueChange = {})
}

/* DEMO 3 */
@Composable
fun NetworkStatus() {
    val context = LocalContext.current
    var isConnected by remember { mutableStateOf(true) }

    DisposableEffect(context) {
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val connectivityManager =
                    context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = connectivityManager.activeNetworkInfo
                isConnected = networkInfo != null && networkInfo.isConnected
            }
        }
        context.registerReceiver(broadcastReceiver, intentFilter)
        onDispose {
            context.unregisterReceiver(broadcastReceiver)
        }
    }

    Text(text = if (isConnected) "Connected" else "Not connected")
}

/* DEMO 4 */
@Composable
fun HandleBackPress(backPressedDispatcher: OnBackPressedDispatcher) {
    val callback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Do something
                Log.d(TAG, "Clicked back press")
            }
        }
    }

    DisposableEffect(key1 = backPressedDispatcher) {
        backPressedDispatcher.addCallback(callback)
        onDispose {
            callback.remove()
        }
    }
}