package com.urukcare.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.urukcare.ui.navigation.Screen
import com.urukcare.ui.theme.*

data class CategoryItem(
    val name: String,
    val color: Color,
    val icon: ImageVector, // Using standard icons for now as placeholders
    val textColor: Color
)

@Composable
fun CategoriesScreen(navController: NavController) {
    val categories = listOf(
        CategoryItem("Painkillers", CategoryPainkillers, Icons.Default.LocalHospital, TextGreen),
        CategoryItem("Antibiotics", CategoryAntibiotics, Icons.Default.LocalHospital, TextGreen),
        CategoryItem("Cardiovascular", CategoryCardio, Icons.Default.Favorite, TextRed),
        CategoryItem("Diabetes", CategoryDiabetes, Icons.Default.LocalHospital, TextPurple), // Need better icon
        CategoryItem("Respiratory", CategoryRespiratory, Icons.Default.LocalHospital, TextBlue), // Need better icon
        CategoryItem("Gastrointestinal", CategoryGastro, Icons.Default.LocalHospital, TextOrange), // Need better icon
        CategoryItem("Vitamins & Supplements", CategoryVitamins, Icons.Default.LocalHospital, TextGreen), // Need better icon
        CategoryItem("Antihistamines", CategoryAntihistamines, Icons.Default.LocalHospital, TextPink) // Need better icon
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Browse by Category",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(0.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories) { category ->
                CategoryCard(category) {
                    navController.navigate(Screen.MedicineList.createRoute(category.name))
                }
            }
        }
    }
}

@Composable
fun CategoryCard(category: CategoryItem, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .height(120.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(category.color)
            .clickable { onClick() }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = category.icon,
                contentDescription = category.name,
                tint = category.textColor,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = category.name,
                color = category.textColor,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                ),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}
