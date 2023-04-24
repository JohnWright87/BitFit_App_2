package com.example.bitfit_app_2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NutritionEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    // Declare an abstract method to get the DAO
    abstract fun NutritionDao(): NutritionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Get the instance of the AppDatabase, create it if it doesn't exist
        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        // Build the database by creating a new instance of the
        // Room.databaseBuilder and specifying the database name and type
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "Nutrition-db"
            ).build()
    }
}