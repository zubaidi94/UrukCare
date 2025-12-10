package com.urukcare.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.urukcare.ui.theme.PrimaryGreen

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(PrimaryGreen.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "About",
                tint = PrimaryGreen,
                modifier = Modifier.size(50.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "UrukCare",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = PrimaryGreen
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Version 1.0.0",
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "UrukCare is a mobile application designed to help end-users easily access accurate and up-to-date information about medicines approved by the government.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Data Source: Demo Data",
            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
            textAlign = TextAlign.Center
        )
    }
}
