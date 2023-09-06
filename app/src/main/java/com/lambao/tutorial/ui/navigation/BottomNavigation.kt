package com.lambao.tutorial.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.lambao.tutorial.ui.screen.NavGraphs
import com.lambao.tutorial.ui.screen.appCurrentDestinationAsState
import com.lambao.tutorial.ui.screen.destinations.CartScreenDestination
import com.lambao.tutorial.ui.screen.destinations.HomeScreenDestination
import com.lambao.tutorial.ui.screen.destinations.ProfileScreenDestination
import com.lambao.tutorial.ui.screen.startAppDestination
import com.lambao.tutorial.ui.theme.LightRed
import com.lambao.tutorial.ui.theme.textStyle
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
) {
    Home(
        HomeScreenDestination,
        "Home",
        Icons.Filled.Home,
        Icons.Outlined.Home
    ),

    Cart(
        CartScreenDestination,
        "Cart",
        Icons.Filled.ShoppingCart,
        Icons.Outlined.ShoppingCart
    ),

    Profile(
        ProfileScreenDestination,
        "Profile",
        Icons.Filled.AccountCircle,
        Icons.Outlined.AccountCircle
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(
    navController: NavController
) {
    val currentDestination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination

    NavigationBar {
        BottomBarDestination.values().forEach { item ->
            NavigationBarItem(
                selected = currentDestination == item.direction,
                onClick = {
                    navController.navigate(item.direction) {
                        launchSingleTop = true
                    }
                },
                label = {
                    Text(
                        text = item.label,
                        style = MaterialTheme.textStyle.regular14,
                        color = if(currentDestination == item.direction) LightRed
                        else Color.LightGray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = LightRed,
                    unselectedIconColor = Color.LightGray,
                    selectedTextColor = LightRed,
                    unselectedTextColor = Color.LightGray
                ),
                icon = {
                    BadgedBox(badge = {
                        if (item.badgeCount != null)
                            Badge {
                                Text(
                                    text = item.badgeCount.toString(),
                                    style = MaterialTheme.textStyle.regular16
                                )
                            }
                    }) {
                        Icon(
                            imageVector = if (currentDestination == item.direction) item.selectedIcon
                            else item.unselectedIcon,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    }
}
