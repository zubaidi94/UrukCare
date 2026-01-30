package com.urukcare.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {
    @Query("SELECT * FROM medicines")
    fun getAllMedicines(): Flow<List<Medicine>>

    @Query("SELECT * FROM medicines WHERE id = :id")
    suspend fun getMedicineById(id: Int): Medicine?

    @Query("SELECT * FROM medicines WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%'")
    fun searchMedicines(query: String): Flow<List<Medicine>>

    @Query("SELECT * FROM medicines WHERE category = :category")
    fun getMedicinesByCategory(category: String): Flow<List<Medicine>>

    @Query("SELECT DISTINCT category FROM medicines")
    fun getAllCategories(): Flow<List<String>>

    @Query("SELECT * FROM medicines WHERE isFavorite = 1")
    fun getFavoriteMedicines(): Flow<List<Medicine>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(medicines: List<Medicine>)

    @Update
    suspend fun update(medicine: Medicine)

    @Query("SELECT COUNT(*) FROM medicines")
    suspend fun getCount(): Int
}
