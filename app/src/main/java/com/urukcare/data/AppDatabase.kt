package com.urukcare.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Medicine::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "urukcare_database"
                )
                .fallbackToDestructiveMigration()
                .addCallback(DatabaseCallback(context))
                .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback(private val context: Context) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                CoroutineScope(Dispatchers.IO).launch {
                    val dao = getDatabase(context).medicineDao()
                    if (dao.getCount() == 0) {
                        populateDatabase(dao)
                    }
                }
            }
        }

        suspend fun populateDatabase(medicineDao: MedicineDao) {
            // Seed the database with initial medicine data (runs only when DB is empty).
            // Dummy data with Euro prices and prescription status
            val medicines = listOf(
                Medicine(
                    name = "Paracetamol 500mg Ratiopharm",
                    price = "€2.49",
                    description = "Pain reliever and fever reducer. Standard pack of 20 tablets.",
                    sideEffects = "Rare: Allergic reactions. Very rare: Liver damage (at high doses).",
                    prescriptionRequired = false,
                    manufacturer = "Ratiopharm GmbH",
                    category = "Painkillers"
                ),
                Medicine(
                    name = "Ibuprofen 400mg ADGC",
                    price = "€3.99",
                    description = "Anti-inflammatory and analgesic. Used for mild to moderate pain.",
                    sideEffects = "Occasional: Stomach pain, nausea. Rare: Headaches.",
                    prescriptionRequired = false,
                    manufacturer = "KSK-Pharma",
                    category = "Painkillers"
                ),
                Medicine(
                    name = "Aspirin Complex",
                    price = "€9.99",
                    description = "Combination for colds with pain and fever.",
                    sideEffects = "Common: Stomach discomfort.",
                    prescriptionRequired = false,
                    manufacturer = "Bayer Vital GmbH",
                    category = "Painkillers"
                ),
                Medicine(
                    name = "Amoxicillin AL 1000",
                    price = "€14.50",
                    description = "Broad-spectrum antibiotic for bacterial infections.",
                    sideEffects = "Common: Diarrhea, nausea, skin rash.",
                    prescriptionRequired = true,
                    manufacturer = "ALIUD PHARMA",
                    category = "Antibiotics"
                ),
                Medicine(
                    name = "Azithromycin 500mg",
                    price = "€18.90",
                    description = "Macrolide antibiotic for respiratory and skin infections.",
                    sideEffects = "Common: Nausea, diarrhea. Rare: Irregular heartbeat.",
                    prescriptionRequired = true,
                    manufacturer = "Hexal AG",
                    category = "Antibiotics"
                ),
                Medicine(
                    name = "Ciprofloxacin 250mg",
                    price = "€16.75",
                    description = "Fluoroquinolone antibiotic for urinary tract infections.",
                    sideEffects = "Common: Nausea, diarrhea. Rare: Tendon problems.",
                    prescriptionRequired = true,
                    manufacturer = "Ratiopharm GmbH",
                    category = "Antibiotics"
                ),
                Medicine(
                    name = "Cetirizin Hexal",
                    price = "€4.20",
                    description = "Antihistamine for hay fever and allergies.",
                    sideEffects = "Common: Drowsiness, dry mouth, headache.",
                    prescriptionRequired = false,
                    manufacturer = "Hexal AG",
                    category = "Antihistamines"
                ),
                Medicine(
                    name = "Loratadin 10mg",
                    price = "€5.50",
                    description = "Non-drowsy antihistamine for allergic rhinitis.",
                    sideEffects = "Uncommon: Headache, dry mouth.",
                    prescriptionRequired = false,
                    manufacturer = "Ratiopharm GmbH",
                    category = "Antihistamines"
                ),
                Medicine(
                    name = "Fexofenadin 180mg",
                    price = "€7.80",
                    description = "Long-acting antihistamine for seasonal allergies.",
                    sideEffects = "Rare: Headache, drowsiness.",
                    prescriptionRequired = false,
                    manufacturer = "1 A Pharma GmbH",
                    category = "Antihistamines"
                ),
                 Medicine(
                    name = "Metformin 1000mg - 1A Pharma",
                    price = "€13.25",
                    description = "Oral diabetes medicine that helps control blood sugar levels.",
                    sideEffects = "Very common: Nausea, vomiting, diarrhea, abdominal pain.",
                    prescriptionRequired = true,
                    manufacturer = "1 A Pharma GmbH",
                    category = "Diabetes"
                ),
                Medicine(
                    name = "Insulin Glargine",
                    price = "€45.00",
                    description = "Long-acting insulin for diabetes management.",
                    sideEffects = "Common: Injection site reactions, hypoglycemia.",
                    prescriptionRequired = true,
                    manufacturer = "Sanofi-Aventis",
                    category = "Diabetes"
                ),
                Medicine(
                    name = "Pantoprazol 20mg",
                    price = "€5.80",
                    description = "Proton pump inhibitor for heartburn and acid reflux.",
                    sideEffects = "Uncommon: Headache, dizziness, diarrhea.",
                    prescriptionRequired = false,
                    manufacturer = "Ratiopharm GmbH",
                    category = "Gastrointestinal"
                ),
                Medicine(
                    name = "Omeprazol 20mg",
                    price = "€6.20",
                    description = "Proton pump inhibitor for stomach ulcers and GERD.",
                    sideEffects = "Common: Headache, nausea, abdominal pain.",
                    prescriptionRequired = false,
                    manufacturer = "Hexal AG",
                    category = "Gastrointestinal"
                ),
                Medicine(
                    name = "Loperamid 2mg",
                    price = "€4.50",
                    description = "Anti-diarrheal medication for acute diarrhea.",
                    sideEffects = "Uncommon: Constipation, dizziness, drowsiness.",
                    prescriptionRequired = false,
                    manufacturer = "Ratiopharm GmbH",
                    category = "Gastrointestinal"
                ),
                Medicine(
                    name = "Ramipril 5mg",
                    price = "€12.10",
                    description = "ACE inhibitor used to treat high blood pressure.",
                    sideEffects = "Common: Dry cough, dizziness, headache.",
                    prescriptionRequired = true,
                    manufacturer = "Hexal AG",
                    category = "Cardiovascular"
                ),
                Medicine(
                    name = "Bisoprolol 5mg",
                    price = "€10.50",
                    description = "Beta-blocker for high blood pressure and heart failure.",
                    sideEffects = "Common: Fatigue, dizziness, cold extremities.",
                    prescriptionRequired = true,
                    manufacturer = "Ratiopharm GmbH",
                    category = "Cardiovascular"
                ),
                Medicine(
                    name = "Atorvastatin 20mg",
                    price = "€15.80",
                    description = "Statin for lowering cholesterol levels.",
                    sideEffects = "Common: Muscle pain, headache, nausea.",
                    prescriptionRequired = true,
                    manufacturer = "Hexal AG",
                    category = "Cardiovascular"
                ),
                Medicine(
                    name = "Vitamin D3 1000 IU",
                    price = "€8.50",
                    description = "Vitamin D supplement for bone health and immune support.",
                    sideEffects = "Rare: Nausea, constipation at high doses.",
                    prescriptionRequired = false,
                    manufacturer = "Doppelherz",
                    category = "Vitamins & Supplements"
                ),
                Medicine(
                    name = "Vitamin B Complex",
                    price = "€12.90",
                    description = "Complete B-vitamin complex for energy and nervous system support.",
                    sideEffects = "Rare: Mild stomach upset.",
                    prescriptionRequired = false,
                    manufacturer = "Ratiopharm GmbH",
                    category = "Vitamins & Supplements"
                ),
                Medicine(
                    name = "Magnesium 400mg",
                    price = "€9.80",
                    description = "Magnesium supplement for muscle and nerve function.",
                    sideEffects = "Common: Diarrhea at high doses.",
                    prescriptionRequired = false,
                    manufacturer = "Doppelherz",
                    category = "Vitamins & Supplements"
                ),
                Medicine(
                    name = "Omega-3 Fish Oil",
                    price = "€15.50",
                    description = "Essential fatty acids for heart and brain health.",
                    sideEffects = "Uncommon: Fishy aftertaste, mild stomach upset.",
                    prescriptionRequired = false,
                    manufacturer = "Doppelherz",
                    category = "Vitamins & Supplements"
                ),
                Medicine(
                    name = "Salbutamol Inhaler",
                    price = "€8.90",
                    description = "Bronchodilator for asthma and COPD relief.",
                    sideEffects = "Common: Tremor, increased heart rate, headache.",
                    prescriptionRequired = true,
                    manufacturer = "GlaxoSmithKline",
                    category = "Respiratory"
                ),
                Medicine(
                    name = "Budesonide Inhaler",
                    price = "€22.50",
                    description = "Corticosteroid inhaler for asthma prevention.",
                    sideEffects = "Common: Oral thrush, hoarse voice.",
                    prescriptionRequired = true,
                    manufacturer = "AstraZeneca",
                    category = "Respiratory"
                ),
                Medicine(
                    name = "ACC Akut 600mg",
                    price = "€7.80",
                    description = "Mucolytic agent to loosen mucus in respiratory conditions.",
                    sideEffects = "Uncommon: Nausea, stomach pain.",
                    prescriptionRequired = false,
                    manufacturer = "Hexal AG",
                    category = "Respiratory"
                )
            )
            medicineDao.insertAll(medicines)
        }
    }
}