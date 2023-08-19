package com.lambao.tutorial

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()

            var textFieldState by remember {
                mutableStateOf("")
            }

            var basicTextFieldState by remember {
                mutableStateOf("")
            }

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                snackbarHost = { SnackbarHost(snackbarHostState) }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TextField(value = textFieldState, label = {
                        Text(
                            text = "Enter your name", modifier = Modifier.background(Color.Cyan)
                        )
                    }, singleLine = true, modifier = Modifier.fillMaxWidth(), onValueChange = {
                        textFieldState = it
                    })

                    Spacer(modifier = Modifier.height(50.dp))
                    Button(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Hello $textFieldState",
                                actionLabel = "Click me",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }) {
                        Text(text = "Show name!")
                    }

                    EditText(
                        value = basicTextFieldState,
                        onValueChanged = { basicTextFieldState = it },
                        modifier = Modifier.fillMaxWidth().background(Color.LightGray).padding(16.dp)
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    Button(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Hello $basicTextFieldState",
                                actionLabel = "Click me",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }) {
                        Text(text = "Show name!")
                    }
                }
            }


        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun ShowSnackBar() {
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }, content = {
            Button(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Hey this is a snackbar",
                        actionLabel = "Click me",
                        duration = SnackbarDuration.Short
                    )
                }
            }) {
                Text(text = "Click me")
            }
        })
    }

    @Composable
    fun EditText(
        value: String,
        onValueChanged: (String) -> Unit,
        modifier: Modifier,
        hint: String = "",
        maxLines: Int = 1
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChanged,
            maxLines = maxLines,
        ) { innerTextField ->
            Box(modifier = modifier) {
                if (value.isEmpty()) {
                    Text(text = hint, color = LocalContentColor.current.copy(alpha = 0.1f))
                }
                innerTextField()
            }
        }
    }
}