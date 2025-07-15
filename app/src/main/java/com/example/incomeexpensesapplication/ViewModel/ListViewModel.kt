package com.example.incomeexpensesapplication.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.incomeexpensesapplication.DataBase.Model.Expenses
import com.example.incomeexpensesapplication.Repository.ExpensesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExpensesViewModel(private val repository: ExpensesRepository) : ViewModel() {
    val expenses: StateFlow<List<Expenses>> = repository.getAllExpenses().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        emptyList()
    )

    fun addExpense(expense: Expenses) {
        viewModelScope.launch {
            repository.insertExpense(expense)
        }
    }

    fun deleteExpense(id: Int) {
        viewModelScope.launch {
            repository.deleteExpense(id)
        }
    }
}

