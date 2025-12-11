package com.urukcare.demo

import com.urukcare.core.AppManager
import com.urukcare.core.ScreenManager
import com.urukcare.ui.navigation.Screen

/**
 * Demo file to demonstrate the singleton pattern in action.
 * Similar to the main() function in the cinema ticket system example.
 * 
 * This file shows how different parts of the app can access the same
 * singleton instances and verify they are indeed the same object.
 */

/**
 * Main demo function to test the singleton pattern
 */
fun main() {
    println("\n========================================")
    println("UrukCare App - Singleton Pattern Demo")
    println("========================================\n")
    
    // Print all available screens
    Screen.printAllScreens()
    
    println("--- Testing AppManager Singleton ---\n")
    
    // Different components of the app obtain the AppManager singleton instance
    val homeScreenManager = AppManager.getInstance()
    println("HomeScreen obtained AppManager instance")
    
    val categoriesScreenManager = AppManager.getInstance()
    println("CategoriesScreen obtained AppManager instance")
    
    val favoritesScreenManager = AppManager.getInstance()
    println("FavoritesScreen obtained AppManager instance")
    
    val aboutScreenManager = AppManager.getInstance()
    println("AboutScreen obtained AppManager instance\n")
    
    // Simulate navigation through the app
    println("--- Simulating User Navigation ---\n")
    
    homeScreenManager.navigateToScreen("Categories")
    categoriesScreenManager.navigateToScreen("Favorites")
    favoritesScreenManager.navigateToScreen("About")
    aboutScreenManager.navigateToScreen("Home")
    homeScreenManager.navigateToScreen("Categories")
    categoriesScreenManager.navigateToScreen("Home")
    
    println()
    
    // Show app summary
    homeScreenManager.showAppSummary()
    
    // Verify that all references point to the same singleton instance
    println("--- Verifying Singleton Instance ---\n")
    println("homeScreenManager === categoriesScreenManager: ${homeScreenManager === categoriesScreenManager}")
    println("categoriesScreenManager === favoritesScreenManager: ${categoriesScreenManager === favoritesScreenManager}")
    println("favoritesScreenManager === aboutScreenManager: ${favoritesScreenManager === aboutScreenManager}")
    
    println("\nInstance addresses (should all be the same):")
    println("  homeScreenManager: $homeScreenManager")
    println("  categoriesScreenManager: $categoriesScreenManager")
    println("  favoritesScreenManager: $favoritesScreenManager")
    println("  aboutScreenManager: $aboutScreenManager")
    
    println("\n--- Testing ScreenManager Singleton ---\n")
    
    // Test the ScreenManager singleton
    val screenTracker1 = ScreenManager.getInstance()
    val screenTracker2 = ScreenManager.getInstance()
    
    // Simulate screen displays
    screenTracker1.onScreenDisplayed("Home")
    screenTracker2.onScreenDisplayed("Categories")
    screenTracker1.onScreenDisplayed("Home")
    screenTracker2.onScreenDisplayed("Favorites")
    screenTracker1.onScreenDisplayed("Home")
    
    println()
    screenTracker1.showScreenStatistics()
    
    // Verify ScreenManager singleton
    println("screenTracker1 === screenTracker2: ${screenTracker1 === screenTracker2}")
    println("Instance addresses:")
    println("  screenTracker1: $screenTracker1")
    println("  screenTracker2: $screenTracker2")
    
    println("\n--- Testing Screen Companion Object ---\n")
    
    // Test Screen companion object methods
    val allScreens = Screen.getAllScreens()
    println("Total screens in app: ${allScreens.size}")
    
    allScreens.forEach { screen ->
        println("  - ${Screen.getDisplayName(screen)}: ${Screen.getDescription(screen)}")
    }
    
    println("\n--- Demo Complete ---\n")
    println("This demo shows that:")
    println("1. AppManager is a singleton - all instances are the same object")
    println("2. ScreenManager is a singleton - all instances are the same object")
    println("3. Screen companion object provides utility methods")
    println("4. The pattern follows the cinema ticket system example\n")
    
    println("========================================\n")
}
