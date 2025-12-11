package com.urukcare.core

/**
 * Interface for managing navigation operations in the app.
 */
interface NavigationManager {
    
    /**
     * Navigate to a specific screen
     * @param screenName The name of the screen to navigate to
     */
    fun navigateToScreen(screenName: String): Unit
    
    /**
     * Get the current screen name
     * @return The name of the current screen
     */
    fun getCurrentScreen(): String
    
    /**
     * Get navigation history
     * @return List of visited screens
     */
    fun getNavigationHistory(): List<String>
}

/**
 * AppManager is a singleton class that manages the application state and navigation.
 * Ensures that there is only one instance managing the app state across
 * all screens and components.
 */
class AppManager private constructor(): NavigationManager {
    
    private var currentScreen: String = "Home"
    private val navigationHistory = mutableListOf<String>()
    private val screenVisitCount = mutableMapOf<String, Int>()
    private var totalNavigations = 0
    
    init {
        navigationHistory.add("Home")
        screenVisitCount["Home"] = 1
        println("AppManager initialized. Starting at Home screen.")
    }
    
    override fun navigateToScreen(screenName: String): Unit {
        currentScreen = screenName
        navigationHistory.add(screenName)
        totalNavigations++
        
        val currentCount = screenVisitCount.getOrDefault(screenName, 0)
        screenVisitCount[screenName] = currentCount + 1
        
        println("Navigated to: $screenName (Visit #${screenVisitCount[screenName]})")
    }
    
    override fun getCurrentScreen(): String {
        return currentScreen
    }
    
    override fun getNavigationHistory(): List<String> {
        return navigationHistory.toList()
    }
    
    /**
     * Display app statistics summary
     */
    fun showAppSummary() {
        println("\n=== UrukCare App Summary ===")
        println("Current Screen: $currentScreen")
        println("Total Navigations: $totalNavigations")
        println("Unique Screens Visited: ${screenVisitCount.size}")
        println("\nScreen Visit Statistics:")
        screenVisitCount.forEach { (screen, count) ->
            println("  - $screen: $count visit(s)")
        }
        println("\nNavigation History: ${navigationHistory.joinToString(" -> ")}")
        println("============================\n")
    }
    
    /**
     * Reset app state
     */
    fun resetAppState() {
        currentScreen = "Home"
        navigationHistory.clear()
        navigationHistory.add("Home")
        screenVisitCount.clear()
        screenVisitCount["Home"] = 1
        totalNavigations = 0
        println("App state reset to initial state.")
    }
    
    companion object {
        private var instance: AppManager? = null
        
        /**
         * Get the singleton instance of AppManager
         */
        fun getInstance(): AppManager {
            if (instance == null) {
                instance = AppManager()
            }
            return instance!!
        }
    }
}
