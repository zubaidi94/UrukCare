package com.urukcare.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.urukcare.ui.theme.PrimaryGreen

/**
 * HomeScreen displays the main landing page with logo, title, and search bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(PrimaryGreen),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Logo",
                tint = Color.White,
                modifier = Modifier.size(70.dp)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 20.dp, end = 20.dp)
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0F2F1))
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "UrukCare",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            ),
            color = Color(0xFF1B1F23)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Medicine Information System",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color(0xFF5F6368),
                fontSize = 18.sp
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { newValue ->
                searchQuery = newValue
                println("Search query changed: $newValue")
            },
            placeholder = { Text("Search medicines...") },
            leadingIcon = { 
                Icon(
                    Icons.Default.Search, 
                    contentDescription = "Search",
                    tint = Color.Gray
                ) 
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = PrimaryGreen,
                unfocusedIndicatorColor = Color(0xFFE0E0E0),
                cursorColor = PrimaryGreen,
                focusedPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray
            ),
            singleLine = true,
            readOnly = false,
            enabled = true
        )
    }
}

/**
 * Object providing metadata and utility methods for HomeScreen.
 */
object HomeScreenInfo {
    const val SCREEN_NAME = "Home"
    const val SCREEN_ROUTE = "home"
    
    fun getTitle(): String = "Home"
    
    fun getDescription(): String = "Search and browse medicines"
    
    fun printScreenInfo() {
        println("Screen: $SCREEN_NAME")
        println("Route: $SCREEN_ROUTE")
        println("Description: ${getDescription()}")
    }
}
