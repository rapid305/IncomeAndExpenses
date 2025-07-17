package com.example.incomeexpensesapplication.Repository


import com.example.incomeexpensesapplication.DataBase.IncomeDao
import com.example.incomeexpensesapplication.DataBase.Model.Income
import kotlinx.coroutines.flow.Flow

class IncomeRepository(private val incomeDao : IncomeDao) {
        fun getAllIncomes(): Flow<List<Income>> = incomeDao.getAllIncome()
        suspend fun insertIncome(income: Income) = incomeDao.insertIncome(income)
        suspend fun deleteIncome(id: Int) = incomeDao.deleteIncome(id)
}