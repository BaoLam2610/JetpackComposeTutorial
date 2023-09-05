package com.lambao.tutorial

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    ToolBarDemo(scrollBehavior)
                }
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    items(200) { item ->
                        Text(text = "Item $item", modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBarDemo(scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(
        title = {
            Text(text = "Hello World")
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Notifications, contentDescription = null)
            }
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = null)
            }
        },
        scrollBehavior = scrollBehavior
    )
}