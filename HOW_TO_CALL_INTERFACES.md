# How to Call Interfaces in Functions

## YES! All 3 Interfaces Work as Real Interfaces

All interfaces can be used in functions. Here's how:

---

## ✅ Interface 1: MedicineDao

### Example Function
```kotlin
suspend fun printAllMedicines(dao: MedicineDao) {
    // Call interface methods
    val medicines = dao.getAllMedicines().first()
    println("Total: ${medicines.size}")
}
```

### How to Call It
```kotlin
// In MainActivity or ViewModel
val dao = database.medicineDao()
printAllMedicines(dao)  // Pass the interface
```

---

## ✅ Interface 2: MedicineDataSource

### Example Function
```kotlin
suspend fun getFavorites(dataSource: MedicineDataSource): Int {
    val favorites = dataSource.getFavoriteMedicines().first()
    return favorites.size
}
```

### How to Call It
```kotlin
// With Local implementation
val localSource = LocalMedicineDataSource(dao)
getFavorites(localSource)

// OR with Remote implementation
val remoteSource = RemoteMedicineDataSource()
getFavorites(remoteSource)

// SAME FUNCTION works with BOTH!
```

---

## ✅ Interface 3: CategoryManager

### Example Function
```kotlin
suspend fun checkCategory(manager: CategoryManager, name: String): Boolean {
    return manager.categoryExists(name)
}
```

### How to Call It
```kotlin
// With Simple implementation
val simple = SimpleCategoryManager(dataSource)
checkCategory(simple, "Pain Relief")

// OR with Cached implementation
val cached = CachedCategoryManager(dataSource)
checkCategory(cached, "Pain Relief")

// SAME FUNCTION works with BOTH!
```

---

## 🎯 Polymorphism in Action

### The Power of Interfaces
```kotlin
// This function accepts the INTERFACE
suspend fun compareDataSources(
    source1: MedicineDataSource,
    source2: MedicineDataSource
) {
    val count1 = source1.getAllMedicines().first().size
    val count2 = source2.getAllMedicines().first().size
    println("Source 1: $count1, Source 2: $count2")
}

// You can pass ANY implementation!
val local = LocalMedicineDataSource(dao)
val remote = RemoteMedicineDataSource()
compareDataSources(local, remote)  // ✅ Works!
```

---

## 📝 Real Example in Your App

### In ViewModel
```kotlin
class MainViewModel(
    private val dataSource: MedicineDataSource  // Interface type!
) : ViewModel() {
    
    fun searchMedicines(query: String) {
        viewModelScope.launch {
            // Call interface method
            dataSource.searchMedicines(query).collect { results ->
                _searchResults.value = results
            }
        }
    }
}
```

### In Repository
```kotlin
class MedicineRepository(
    private val dataSource: MedicineDataSource  // Interface type!
) {
    
    suspend fun toggleFavorite(medicine: Medicine) {
        val updated = medicine.copy(isFavorite = !medicine.isFavorite)
        // Call interface method
        dataSource.updateMedicine(updated)
    }
}
```

---

## 📊 Summary

| Interface | Can Be Called? | Example Function Parameter |
|-----------|----------------|---------------------------|
| **MedicineDao** | ✅ YES | `fun example(dao: MedicineDao)` |
| **MedicineDataSource** | ✅ YES | `fun example(source: MedicineDataSource)` |
| **CategoryManager** | ✅ YES | `fun example(manager: CategoryManager)` |

**All interfaces are fully functional and can be used in any function!**

---

## 💡 Key Point

When you use an **interface as a parameter type**, the function can accept **ANY implementation** of that interface. This is the power of polymorphism!

```kotlin
// Function accepts interface
fun doSomething(manager: CategoryManager) {
    // Works with SimpleCategoryManager
    // Works with CachedCategoryManager
    // Works with ANY future implementation!
}
```

See [`InterfaceUsageExamples.kt`](file:///Users/zubaidi/HTW/Mobile%20Computing%20/UrukCare-final%20version/app/src/main/java/com/urukcare/examples/InterfaceUsageExamples.kt) for complete working examples!
