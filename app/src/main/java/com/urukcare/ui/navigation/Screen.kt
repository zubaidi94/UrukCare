package com.urukcare.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Categories : Screen("categories")
    object Favorites : Screen("favorites")
    object About : Screen("about")
}
