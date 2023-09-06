package com.lambao.tutorial.ui.screen

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object ProductDetailScreen : Screen("product_detail_screen")
    object CartScreen : Screen("cart_screen")

}