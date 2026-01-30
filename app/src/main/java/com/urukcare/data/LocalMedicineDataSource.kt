package com.urukcare.data

import kotlinx.coroutines.flow.Flow


class LocalMedicineDataSource(
    private val medicineDao: MedicineDao
) : MedicineDataSource {
    
    override fun getAllMedicines(): Flow<List<Medicine>> {
        return medicineDao.getAllMedicines()
    }
    
    override fun searchMedicines(query: String): Flow<List<Medicine>> {
        return medicineDao.searchMedicines(query)
    }
    
    override fun getMedicinesByCategory(category: String): Flow<List<Medicine>> {
        return medicineDao.getMedicinesByCategory(category)
    }
    
    override fun getAllCategories(): Flow<List<String>> {
        return medicineDao.getAllCategories()
    }
    
    override fun getFavoriteMedicines(): Flow<List<Medicine>> {
        return medicineDao.getFavoriteMedicines()
    }
    
    override suspend fun getMedicineById(id: Int): Medicine? {
        return medicineDao.getMedicineById(id)
    }
    
    override suspend fun updateMedicine(medicine: Medicine) {
        medicineDao.update(medicine)
    }
}
