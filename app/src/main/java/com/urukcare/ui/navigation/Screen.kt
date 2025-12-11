package com.urukcare.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Sealed class representing all screens in the UrukCare application.
 */
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Categories : Screen("categories")
    object Favorites : Screen("favorites")
    object About : Screen("about")
    
    companion object {
        
        fun getAllScreens(): List<Screen> {
            return listOf(Home, Categories, Favorites, About)
        }
        
        fun getScreenByRoute(route: String): Screen? {
            return when (route) {
                "home" -> Home
                "categories" -> Categories
                "favorites" -> Favorites
                "about" -> About
                else -> null
            }
        }
        
        fun getDisplayName(screen: Screen): String {
            return when (screen) {
                is Home -> "Home"
                is Categories -> "Categories"
                is Favorites -> "Favorites"
                is About -> "About"
            }
        }
        
        fun getIcon(screen: Screen): ImageVector {
            return when (screen) {
                is Home -> Icons.Default.Home
                is Categories -> Icons.Default.Apps
                is Favorites -> Icons.Default.Favorite
                is About -> Icons.Default.Info
            }
        }
        
        fun getDescription(screen: Screen): String {
            return when (screen) {
                is Home -> "Search and browse medicines"
                is Categories -> "Browse by medicine categories"
                is Favorites -> "Your favorite medicines"
                is About -> "About UrukCare app"
            }
        }
        
        fun printAllScreens() {
            println("\n=== UrukCare Screens ===")
            getAllScreens().forEach { screen ->
                println("${getDisplayName(screen)}: ${getDescription(screen)} (route: ${screen.route})")
            }
            println("========================\n")
        }
    }
}
