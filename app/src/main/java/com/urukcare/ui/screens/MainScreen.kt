package com.urukcare.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.urukcare.ui.navigation.Screen
import com.urukcare.ui.theme.PrimaryGreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                val items = listOf(
                    Screen.Home to "Home",
                    Screen.Categories to "Categories",
                    Screen.Favorites to "Favorites",
                    Screen.About to "About"
                )
                
                val icons = listOf(
                    Icons.Default.Home,
                    Icons.Default.Apps, // Using Apps for Categories
                    Icons.Default.Favorite,
                    Icons.Default.Info
                )

                items.forEachIndexed { index, (screen, label) ->
                    NavigationBarItem(
                        icon = { Icon(icons[index], contentDescription = label) },
                        label = { Text(label) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PrimaryGreen,
                            selectedTextColor = PrimaryGreen,
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController = navController, startDestination = Screen.Home.route) {
                composable(Screen.Home.route) {
                    HomeScreen()
                }
                composable(Screen.Categories.route) {
                    CategoriesScreen()
                }
                composable(Screen.Favorites.route) {
                    FavoritesScreen()
                }
                composable(Screen.About.route) {
                    AboutScreen()
                }
            }
        }
    }
}
