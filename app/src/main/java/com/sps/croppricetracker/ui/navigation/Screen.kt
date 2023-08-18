package com.sps.croppricetracker.ui.navigation

sealed class Screen(val route : String) {
    object SplashScreen : Screen("splash_screen")
    object HomePage : Screen("home_screen")
    object LoginPage : Screen("login_screen")
    object Register : Screen("register_screen")
}
