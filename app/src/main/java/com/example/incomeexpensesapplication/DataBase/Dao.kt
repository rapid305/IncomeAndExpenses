package com.example.incomeexpensesapplication.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.incomeexpensesapplication.DataBase.Model.Expenses
import com.example.incomeexpensesapplication.DataBase.Model.Income
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpensesDao {
    @Query("SELECT * FROM Expenses")
    fun getAllExpenses(): Flow<List<Expenses>>

    @Query("DELETE FROM Expenses WHERE id = :id")
    suspend fun deleteExpenses(id: Int): Int

    @Insert
    suspend fun insertExpense(expenses: Expenses): Long
}

@Dao
interface IncomeDao {
    @Query("SELECT * FROM Income")
    fun getAllIncome(): Flow<List<Income>>

    @Query("DELETE FROM Income WHERE id = :id")
    suspend fun deleteIncome(id: Int): Int

    @Insert
    suspend fun insertIncome(income: Income): Long
}