package com.urukcare.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class RemoteMedicineDataSource : MedicineDataSource {
    
    private val mockMedicines = listOf(
        Medicine(
            id = 1001,
            name = "Remote Medicine A",
            price = "15.99€",
            description = "Mock medicine from remote source",
            sideEffects = "May cause drowsiness",
            prescriptionRequired = false,
            manufacturer = "Remote Pharma GmbH",
            category = "Pain Relief",
            imageUrl = null,
            isFavorite = false
        ),
        Medicine(
            id = 1002,
            name = "Remote Medicine B",
            price = "25.50€",
            description = "Another mock medicine from remote source",
            sideEffects = "Nausea, headache",
            prescriptionRequired = true,
            manufacturer = "Mock Pharmaceuticals",
            category = "Antibiotics",
            imageUrl = null,
            isFavorite = false
        )
    )
    
    override fun getAllMedicines(): Flow<List<Medicine>> {
        return flowOf(mockMedicines)
    }
    
    override fun searchMedicines(query: String): Flow<List<Medicine>> {
        val filtered = mockMedicines.filter { 
            it.name.contains(query, ignoreCase = true) 
        }
        return flowOf(filtered)
    }
    
    override fun getMedicinesByCategory(category: String): Flow<List<Medicine>> {
        val filtered = mockMedicines.filter { it.category == category }
        return flowOf(filtered)
    }
    
    override fun getAllCategories(): Flow<List<String>> {
        val categories = mockMedicines.map { it.category }.distinct()
        return flowOf(categories)
    }
    
    override fun getFavoriteMedicines(): Flow<List<Medicine>> {
        val favorites = mockMedicines.filter { it.isFavorite }
        return flowOf(favorites)
    }
    
    override suspend fun getMedicineById(id: Int): Medicine? {
        return mockMedicines.find { it.id == id }
    }
    
    override suspend fun updateMedicine(medicine: Medicine) {
        // In a real implementation, this would make an API call to update the medicine
        // For mock purposes, we just simulate success
        println("Mock: Updated medicine ${medicine.name}")
    }
}
