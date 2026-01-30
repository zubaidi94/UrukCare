package com.urukcare.data


interface CategoryManager {
    
    /**
     * Get all available categories
     */
    suspend fun getAllCategories(): List<String>
    
    /**
     * Get category count
     */
    suspend fun getCategoryCount(): Int
    
    /**
     * Check if a category exists
     */
    suspend fun categoryExists(categoryName: String): Boolean
    
    /**
     * Get medicines count for a specific category
     */
    suspend fun getMedicineCountInCategory(categoryName: String): Int
}
