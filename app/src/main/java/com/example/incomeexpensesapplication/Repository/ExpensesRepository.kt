package com.example.incomeexpensesapplication.Repository

import com.example.incomeexpensesapplication.DataBase.ExpensesDao
import com.example.incomeexpensesapplication.DataBase.Model.Expenses
import kotlinx.coroutines.flow.Flow

class ExpensesRepository(private val expensesDao: ExpensesDao) {
    fun getAllExpenses(): Flow<List<Expenses>> = expensesDao.getAllExpenses()
    suspend fun insertExpense(expense: Expenses) = expensesDao.insertExpense(expense)
    suspend fun deleteExpense(id: Int) = expensesDao.deleteExpenses(id)
}