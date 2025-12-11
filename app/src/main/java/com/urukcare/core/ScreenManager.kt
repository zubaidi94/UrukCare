package com.urukcare.core

/**
 * Interface for managing screen-related operations
 */
interface ScreenController {
    
    /**
     * Track when a screen is displayed
     * @param screenName The name of the screen
     */
    fun onScreenDisplayed(screenName: String): Unit
    
    /**
     * Get screen statistics
     * @return Map of screen names to visit counts
     */
    fun getScreenStatistics(): Map<String, Int>
}

/**
 * ScreenManager is a singleton class that manages screen tracking and statistics.
 */
class ScreenManager private constructor(): ScreenController {
    
    private val screenDisplayTimes = mutableMapOf<String, MutableList<Long>>()
    private val screenDisplayCount = mutableMapOf<String, Int>()
    
    override fun onScreenDisplayed(screenName: String): Unit {
        val currentTime = System.currentTimeMillis()
        
        if (!screenDisplayTimes.containsKey(screenName)) {
            screenDisplayTimes[screenName] = mutableListOf()
        }
        screenDisplayTimes[screenName]?.add(currentTime)
        
        val count = screenDisplayCount.getOrDefault(screenName, 0)
        screenDisplayCount[screenName] = count + 1
        
        println("Screen displayed: $screenName (Total displays: ${screenDisplayCount[screenName]})")
    }
    
    override fun getScreenStatistics(): Map<String, Int> {
        return screenDisplayCount.toMap()
    }
    
    /**
     * Show detailed screen statistics
     */
    fun showScreenStatistics() {
        println("\n=== Screen Statistics ===")
        println("Total Screens Tracked: ${screenDisplayCount.size}")
        screenDisplayCount.forEach { (screen, count) ->
            println("  - $screen: $count display(s)")
        }
        println("=========================\n")
    }
    
    companion object {
        private var instance: ScreenManager? = null
        
        /**
         * Get the singleton instance of ScreenManager
         */
        fun getInstance(): ScreenManager {
            if (instance == null) {
                instance = ScreenManager()
            }
            return instance!!
        }
    }
}
