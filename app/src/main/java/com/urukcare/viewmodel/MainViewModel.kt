package com.urukcare.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.urukcare.data.Medicine
import com.urukcare.data.MedicineRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MedicineRepository) : ViewModel() {

    // Search State
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Search Results
    val searchResults: StateFlow<List<Medicine>> = _searchQuery
        .flatMapLatest { query ->
            if (query.isBlank()) {
                repository.getAllMedicines()
            } else {
                repository.searchMedicines(query)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Categories
    val categories: StateFlow<List<String>> = repository.getAllCategories()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Selected Category State
    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory.asStateFlow()

    // Medicines in Selected Category
    val medicinesByCategory: StateFlow<List<Medicine>> = _selectedCategory
        .flatMapLatest { category ->
            if (category == null) {
                repository.getAllMedicines() // Or empty list if you prefer
            } else {
                repository.getMedicinesByCategory(category)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Favorites
    val favoriteMedicines: StateFlow<List<Medicine>> = repository.getFavoriteMedicines()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun onCategorySelected(category: String) {
        _selectedCategory.value = category
    }

    fun clearCategorySelection() {
        _selectedCategory.value = null
    }

    fun toggleFavorite(medicine: Medicine) {
        viewModelScope.launch {
            repository.toggleFavorite(medicine)
        }
    }
    
    suspend fun getMedicineById(id: Int): Medicine? {
        return repository.getMedicineById(id)
    }
}

class MainViewModelFactory(private val repository: MedicineRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
