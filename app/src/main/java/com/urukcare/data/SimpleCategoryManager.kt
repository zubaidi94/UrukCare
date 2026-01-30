package com.urukcare.data

import kotlinx.coroutines.flow.first

/**
 * Simple implementation of CategoryManager.
 * Directly queries the data source without any caching or optimization.
 */
class SimpleCategoryManager(
    private val dataSource: MedicineDataSource
) : CategoryManager {
    
    override suspend fun getAllCategories(): List<String> {
        return dataSource.getAllCategories().first()
    }
    
    override suspend fun getCategoryCount(): Int {
        return dataSource.getAllCategories().first().size
    }
    
    override suspend fun categoryExists(categoryName: String): Boolean {
        val categories = dataSource.getAllCategories().first()
        return categories.contains(categoryName)
    }
    
    override suspend fun getMedicineCountInCategory(categoryName: String): Int {
        val medicines = dataSource.getMedicinesByCategory(categoryName).first()
        return medicines.size
    }
}
