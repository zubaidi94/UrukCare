package com.urukcare.data

import kotlinx.coroutines.flow.Flow


class MedicineRepository(private val dataSource: MedicineDataSource) {

    fun getAllMedicines(): Flow<List<Medicine>> = dataSource.getAllMedicines()

    fun searchMedicines(query: String): Flow<List<Medicine>> = dataSource.searchMedicines(query)

    fun getMedicinesByCategory(category: String): Flow<List<Medicine>> = dataSource.getMedicinesByCategory(category)

    fun getAllCategories(): Flow<List<String>> = dataSource.getAllCategories()

    fun getFavoriteMedicines(): Flow<List<Medicine>> = dataSource.getFavoriteMedicines()

    suspend fun getMedicineById(id: Int): Medicine? = dataSource.getMedicineById(id)

    suspend fun toggleFavorite(medicine: Medicine) {
        val updatedMedicine = medicine.copy(isFavorite = !medicine.isFavorite)
        dataSource.updateMedicine(updatedMedicine)
    }
}
