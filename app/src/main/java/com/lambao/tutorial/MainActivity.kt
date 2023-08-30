package com.lambao.tutorial

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val permissionState = rememberMultiplePermissionsState(
                permissions = listOf(
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.CAMERA
                )
            )

            var cameraPermissionState by remember {
                mutableStateOf("")
            }

            var recordAudioPermissionState by remember {
                mutableStateOf("")
            }

            val lifecycleOwner = LocalLifecycleOwner.current
            DisposableEffect(key1 = lifecycleOwner) {
                val observer = LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_START) {
                        permissionState.launchMultiplePermissionRequest()
                    }
                }
                lifecycleOwner.lifecycle.addObserver(observer)
                onDispose {
                    lifecycleOwner.lifecycle.removeObserver(observer)
                }
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                permissionState.permissions.forEach { perm ->
                    when (perm.permission) {
                        Manifest.permission.CAMERA -> {
                            cameraPermissionState = when {
                                perm.status.isGranted -> "Camera is accepted"
                                perm.status.shouldShowRationale -> "Camera is needed to access"
                                perm.status.isPermanentlyDenied() -> "Camera is denied, please enabled in settings"
                                else -> "what is camera?"
                            }
                        }

                        Manifest.permission.RECORD_AUDIO -> {
                            recordAudioPermissionState = when {
                                perm.status.isGranted -> "Record audio is accepted"
                                perm.status.shouldShowRationale -> "Record audio is needed to access"
                                perm.status.isPermanentlyDenied() -> "Record audio is denied, please enabled in settings"
                                else -> "what is Record audio?"
                            }
                        }
                    }
                }
                Text(text = cameraPermissionState)
                Text(text = recordAudioPermissionState)
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
fun PermissionStatus.isPermanentlyDenied() = !shouldShowRationale && !isGranted