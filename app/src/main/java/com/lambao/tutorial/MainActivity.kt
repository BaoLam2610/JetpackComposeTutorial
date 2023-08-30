package com.lambao.tutorial

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lambao.BottomNavItem

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        list = listOf(
                            BottomNavItem(
                                name = "Home",
                                route = "home",
                                icon = Icons.Default.Home
                            ),
                            BottomNavItem(
                                name = "Message",
                                route = "message",
                                icon = Icons.Default.Email,
                                badgeCount = 200
                            ),
                            BottomNavItem(
                                name = "Settings",
                                route = "settings",
                                icon = Icons.Default.Settings,
                                20
                            )
                        ),
                        navController = navController
                    ) {
                        navController.navigate(it.route)
                    }
                }
            ) {
                Navigation(navController = navController)
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }

        composable("message") {
            MessageScreen()
        }

        composable("settings") {
            SettingsScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    list: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(
        modifier = modifier,
        containerColor = Color.LightGray,
        tonalElevation = 5.dp
    ) {
        list.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.Red,
                    indicatorColor = Color.Yellow
                ),
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if (item.badgeCount > 0) {
                            BadgedBox(badge = {
                                Text(
                                    text = item.badgeCount.toString(),
                                    modifier = Modifier
                                        .background(Color.Red, RoundedCornerShape(6.dp))
                                        .padding(2.dp),
                                    fontSize = 10.sp,
                                    color = Color.White
                                )
                            }) {
                                Icon(imageVector = item.icon, contentDescription = item.name)
                            }
                        } else {
                            Icon(imageVector = item.icon, contentDescription = item.name)
                        }
//                        if (selected)
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                    }
                })
        }
    }
}

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home Screen")
    }
}

@Composable
fun MessageScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Message Screen")
    }
}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Settings Screen")
    }
}