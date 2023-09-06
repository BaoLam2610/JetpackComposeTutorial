package com.lambao.tutorial.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lambao.tutorial.ui.screen.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) {
        NavHost(navController = navController)
    }
}

@Composable
fun NavHost(
    navController: NavHostController
) {
    DestinationsNavHost(
        navGraph = NavGraphs.root,
        navController = navController
    )
}