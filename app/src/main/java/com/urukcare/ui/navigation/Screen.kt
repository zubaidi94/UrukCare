package com.urukcare.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Categories : Screen("categories")
    object Favorites : Screen("favorites")
    object About : Screen("about")
    object MedicineList : Screen("medicine_list/{category}") {
        fun createRoute(category: String) = "medicine_list/$category"
    }
    object MedicineDetail : Screen("medicine_detail/{medicineId}") {
        fun createRoute(medicineId: Int) = "medicine_detail/$medicineId"
    }
}
