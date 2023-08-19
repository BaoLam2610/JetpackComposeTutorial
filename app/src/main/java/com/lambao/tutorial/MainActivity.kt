package com.lambao.tutorial

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumn {
                itemsIndexed(
                    listOf("Hello","World")
                ) { index, item ->
                    Text(
                        text = "Item $index, value $item",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        textAlign = TextAlign.Center
                    )
                }
                /*repeat(5000) {
                    item {
                        Text(
                            text = "Item $it",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }*/
            }
        }
    }

    @Composable
    fun Scroll() {
        val state = rememberScrollState()
        Column(
            modifier = Modifier.verticalScroll(state),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(500) {
                Text(
                    text = "Item $it",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
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