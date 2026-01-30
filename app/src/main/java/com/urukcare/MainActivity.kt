package com.urukcare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.urukcare.data.AppDatabase
import com.urukcare.data.MedicineRepository
import com.urukcare.ui.screens.MainScreen
import com.urukcare.ui.theme.UrukCareTheme
import com.urukcare.viewmodel.MainViewModel
import com.urukcare.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val database = AppDatabase.getDatabase(this)
        
        // Create the data source implementation (using local database)
        val dataSource = com.urukcare.data.LocalMedicineDataSource(database.medicineDao())
        
        // Pass the interface to the repository
        val repository = MedicineRepository(dataSource)
        val viewModelFactory = MainViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        setContent {
            UrukCareTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(viewModel)
                }
            }
        }
    }
}
