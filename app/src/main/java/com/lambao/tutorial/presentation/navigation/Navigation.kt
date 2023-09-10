package com.lambao.tutorial.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.lambao.tutorial.presentation.screen.coin.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupNavigation() {
    Scaffold {
        NavHost()
    }
}

@Composable
fun NavHost(
) {
    DestinationsNavHost(
        navGraph = NavGraphs.root,
    )
}