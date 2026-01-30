package com.urukcare.examples

import com.urukcare.data.CategoryManager
import com.urukcare.data.MedicineDao
import com.urukcare.data.MedicineDataSource
import kotlinx.coroutines.flow.first

/**
 * DEMONSTRATION: How to use all 3 interfaces in real functions
 * This file shows that all interfaces can be called and used in actual code
 */
class InterfaceUsageExamples {
    
    // ========================================
    // INTERFACE 1: MedicineDao Usage
    // ========================================
    
    /**
     * Function that accepts MedicineDao interface
     * Can work with ANY implementation of MedicineDao
     */
    suspend fun printAllMedicinesFromDao(dao: MedicineDao) {
        // Call interface methods
        val medicines = dao.getAllMedicines().first()
        println("Total medicines from DAO: ${medicines.size}")
        
        // Call another interface method
        val count = dao.getCount()
        println("Count from DAO: $count")
    }
    
    /**
     * Function that searches using MedicineDao interface
     */
    suspend fun searchWithDao(dao: MedicineDao, query: String) {
        val results = dao.searchMedicines(query).first()
        println("Found ${results.size} medicines matching '$query'")
    }
    
    // ========================================
    // INTERFACE 2: MedicineDataSource Usage
    // ========================================
    
    /**
     * Function that accepts MedicineDataSource interface
     * Works with LocalMedicineDataSource OR RemoteMedicineDataSource
     */
    suspend fun printAllMedicinesFromDataSource(dataSource: MedicineDataSource) {
        // Call interface methods
        val medicines = dataSource.getAllMedicines().first()
        println("Total medicines from DataSource: ${medicines.size}")
        
        // Call another interface method
        val medicine = dataSource.getMedicineById(1)
        println("Medicine with ID 1: ${medicine?.name}")
    }
    
    /**
     * Function that gets favorites using interface
     */
    suspend fun getFavoritesCount(dataSource: MedicineDataSource): Int {
        val favorites = dataSource.getFavoriteMedicines().first()
        return favorites.size
    }
    
    /**
     * Function that searches by category using interface
     */
    suspend fun getMedicinesInCategory(
        dataSource: MedicineDataSource, 
        category: String
    ) {
        val medicines = dataSource.getMedicinesByCategory(category).first()
        println("Category '$category' has ${medicines.size} medicines")
    }
    
    // ========================================
    // INTERFACE 3: CategoryManager Usage
    // ========================================
    
    /**
     * Function that accepts CategoryManager interface
     * Works with SimpleCategoryManager OR CachedCategoryManager
     */
    suspend fun printCategoryInfo(manager: CategoryManager) {
        // Call interface methods
        val categories = manager.getAllCategories()
        val count = manager.getCategoryCount()
        
        println("Total categories: $count")
        println("Categories: ${categories.joinToString(", ")}")
    }
    
    /**
     * Function that checks if category exists using interface
     */
    suspend fun checkCategory(manager: CategoryManager, categoryName: String): Boolean {
        val exists = manager.categoryExists(categoryName)
        println("Category '$categoryName' exists: $exists")
        return exists
    }
    
    /**
     * Function that gets medicine count per category
     */
    suspend fun analyzeCategorySize(manager: CategoryManager, categoryName: String) {
        val count = manager.getMedicineCountInCategory(categoryName)
        println("Category '$categoryName' contains $count medicines")
    }
    
    // ========================================
    // POLYMORPHISM DEMONSTRATION
    // ========================================
    
    /**
     * This function demonstrates POLYMORPHISM
     * It accepts the INTERFACE type, so it can work with ANY implementation
     */
    suspend fun compareDataSources(
        source1: MedicineDataSource,  // Could be Local
        source2: MedicineDataSource   // Could be Remote
    ) {
        val count1 = source1.getAllMedicines().first().size
        val count2 = source2.getAllMedicines().first().size
        
        println("Source 1 has $count1 medicines")
        println("Source 2 has $count2 medicines")
    }
    
    /**
     * This function demonstrates switching between implementations
     */
    suspend fun compareCategoryManagers(
        simple: CategoryManager,   // SimpleCategoryManager
        cached: CategoryManager    // CachedCategoryManager
    ) {
        val count1 = simple.getCategoryCount()
        val count2 = cached.getCategoryCount()
        
        println("Simple manager found $count1 categories")
        println("Cached manager found $count2 categories")
    }
}

/**
 * EXAMPLE USAGE IN REAL CODE
 */
class RealWorldExample(
    private val medicineDao: MedicineDao,
    private val dataSource: MedicineDataSource,
    private val categoryManager: CategoryManager
) {
    
    /**
     * Real function that uses all 3 interfaces
     */
    suspend fun generateReport() {
        println("=== Medicine Report ===")
        
        // Use MedicineDao interface
        val totalCount = medicineDao.getCount()
        println("Total medicines in database: $totalCount")
        
        // Use MedicineDataSource interface
        val favorites = dataSource.getFavoriteMedicines().first()
        println("Favorite medicines: ${favorites.size}")
        
        // Use CategoryManager interface
        val categoryCount = categoryManager.getCategoryCount()
        println("Total categories: $categoryCount")
    }
    
    /**
     * Search function using interface
     */
    suspend fun searchMedicine(query: String) {
        // This works because dataSource is an INTERFACE
        // It doesn't matter if it's Local or Remote implementation
        val results = dataSource.searchMedicines(query).first()
        println("Search results for '$query': ${results.size} found")
    }
}
