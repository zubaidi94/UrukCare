package com.urukcare.data

import kotlinx.coroutines.flow.first


class CachedCategoryManager(
    private val dataSource: MedicineDataSource
) : CategoryManager {
    
    // Cache for categories
    private var cachedCategories: List<String>? = null
    private var lastCacheTime: Long = 0
    private val cacheValidityDuration = 60_000L // 60 seconds
    
    /**
     * Check if cache is still valid
     */
    private fun isCacheValid(): Boolean {
        return cachedCategories != null && 
               (System.currentTimeMillis() - lastCacheTime) < cacheValidityDuration
    }
    
    /**
     * Get categories from cache or fetch from data source
     */
    private suspend fun getCategories(): List<String> {
        if (isCacheValid()) {
            return cachedCategories!!
        }
        
        // Fetch from data source and update cache
        val categories = dataSource.getAllCategories().first()
        cachedCategories = categories
        lastCacheTime = System.currentTimeMillis()
        return categories
    }
    
    override suspend fun getAllCategories(): List<String> {
        return getCategories()
    }
    
    override suspend fun getCategoryCount(): Int {
        return getCategories().size
    }
    
    override suspend fun categoryExists(categoryName: String): Boolean {
        return getCategories().contains(categoryName)
    }
    
    override suspend fun getMedicineCountInCategory(categoryName: String): Int {
        val medicines = dataSource.getMedicinesByCategory(categoryName).first()
        return medicines.size
    }
    
    /**
     * Clear the cache manually if needed
     */
    fun clearCache() {
        cachedCategories = null
        lastCacheTime = 0
    }
}
