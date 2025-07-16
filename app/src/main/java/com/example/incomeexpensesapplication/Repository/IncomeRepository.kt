package com.example.incomeexpensesapplication.Repository

import com.example.incomeexpensesapplication.DataBase.ExpensesDao
import com.example.incomeexpensesapplication.DataBase.IncomeDao
import com.example.incomeexpensesapplication.DataBase.Model.Expenses
import com.example.incomeexpensesapplication.DataBase.Model.Income
import kotlinx.coroutines.flow.Flow

class IncomeRepository(private val incomeDao : IncomeDao) {
        fun getAllIncomes(): Flow<List<Income>> = incomeDao.getAllIncome()
        suspend fun insertExpense(income: Income) = incomeDao.insertIncome(income)
        suspend fun deleteExpense(id: Int) = incomeDao.deleteIncome(id)
}