package com.urukcare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.urukcare.core.AppManager
import com.urukcare.core.ScreenManager
import com.urukcare.ui.screens.MainScreen
import com.urukcare.ui.theme.UrukCareTheme

/**
 * MainActivity is the entry point of the UrukCare application.
 * It initializes the singleton instances and sets up the UI.
 */
class MainActivity : ComponentActivity() {
    
    private val appManager = AppManager.getInstance()
    private val screenManager = ScreenManager.getInstance()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        println("MainActivity created - AppManager instance: $appManager")
        println("MainActivity created - ScreenManager instance: $screenManager")
        
        appManager.navigateToScreen("Home")
        screenManager.onScreenDisplayed("Home")

        setContent {
            UrukCareTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        appManager.showAppSummary()
        screenManager.showScreenStatistics()
    }
}
