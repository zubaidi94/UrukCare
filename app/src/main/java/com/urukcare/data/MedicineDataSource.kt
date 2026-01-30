package com.urukcare.data

import kotlinx.coroutines.flow.Flow


interface MedicineDataSource {
    
    /**
     * Get all medicines from the data source
     */
    fun getAllMedicines(): Flow<List<Medicine>>
    
    /**
     * Search medicines by name
     */
    fun searchMedicines(query: String): Flow<List<Medicine>>
    
    /**
     * Get medicines filtered by category
     */
    fun getMedicinesByCategory(category: String): Flow<List<Medicine>>
    
    /**
     * Get all available categories
     */
    fun getAllCategories(): Flow<List<String>>
    
    /**
     * Get all favorite medicines
     */
    fun getFavoriteMedicines(): Flow<List<Medicine>>
    
    /**
     * Get a specific medicine by ID
     */
    suspend fun getMedicineById(id: Int): Medicine?
    
    /**
     * Update a medicine (e.g., toggle favorite status)
     */
    suspend fun updateMedicine(medicine: Medicine)
}
