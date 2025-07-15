package com.example.incomeexpensesapplication.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.incomeexpensesapplication.DataBase.Model.Expenses
import com.example.incomeexpensesapplication.DataBase.Model.Income


@Database(
    entities = [Income::class, Expenses::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDaoExpenses(): ExpensesDao
    abstract fun getDaoIncome(): IncomeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app-database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}




