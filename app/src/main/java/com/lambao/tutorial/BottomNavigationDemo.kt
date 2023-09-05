@file:OptIn(ExperimentalMaterial3Api::class)

package com.lambao.tutorial

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun NavigationBarDemo() {
    val items = listOf(
        BottomNavigationItem(
            "Home",
            Icons.Filled.Home,
            Icons.Outlined.Home,
            false
        ),
        BottomNavigationItem(
            "Message",
            Icons.Filled.Email,
            Icons.Outlined.Email,
            false,
            badgeCount = 22
        ),
        BottomNavigationItem(
            "Home",
            Icons.Filled.Settings,
            Icons.Outlined.Settings,
            true
        )
    )

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                },
                label = {
                    Text(text = item.title)
                },
                icon = {
                    BadgedBox(badge = {
                        if (item.badgeCount != null)
                            Badge {
                                Text(text = item.badgeCount.toString())
                            }
                        else if(item.hasNews)
                            Badge()
                    }) {
                        Icon(
                            imageVector = if (selectedItemIndex == index) item.selectedIcon else item.unselectedIcon,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)