package com.urukcare.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines")
data class Medicine(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: String,
    val description: String,
    val sideEffects: String,
    val prescriptionRequired: Boolean,
    val manufacturer: String,
    val category: String,
    val imageUrl: String? = null,
    val isFavorite: Boolean = false
)
