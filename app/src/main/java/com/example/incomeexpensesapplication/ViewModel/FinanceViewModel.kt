package com.example.incomeexpensesapplication.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.incomeexpensesapplication.DataBase.Model.Expenses
import com.example.incomeexpensesapplication.DataBase.Model.FinancialRecords
import com.example.incomeexpensesapplication.DataBase.Model.Income
import com.example.incomeexpensesapplication.Repository.ExpensesRepository
import com.example.incomeexpensesapplication.Repository.IncomeRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.combine

class GeneralViewModel(
    private val incomeRepo : IncomeRepository,
    private val expenseRepo : ExpensesRepository
) : ViewModel() {
    /*val IncomeRepository = incomeRepo
    val ExpensesRepository = expenseRepo*/
    /*private val incomeRepo = IncomeRepository(),
    private val expenseRepo = ExpensesRepository()*/

    val incomes: StateFlow<List<Income>> = incomeRepo.getAllIncomes().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        emptyList()
    )
    val expenses: StateFlow<List<Expenses>> = expenseRepo.getAllExpenses().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        emptyList()
    )

    val allFinance : StateFlow<List<FinancialRecords>> = combine(incomes , expenses) {incomesList ,expensesList ->
        (incomesList + expensesList)/*.sortedByDescending { it.date }*/
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        emptyList()
    )


    fun addIncome(income: Income) {
        viewModelScope.launch {
            incomeRepo.insertIncome(income)
        }
    }

    fun deleteIncome(id: Int) {
        viewModelScope.launch {
            incomeRepo.deleteIncome(id)
        }
    }
    fun addExpense(expense: Expenses) {
        viewModelScope.launch {
            expenseRepo.insertExpense(expense)
        }
    }
    fun deleteExpense(id: Int) {
        viewModelScope.launch {
            expenseRepo.deleteExpense(id)
        }
    }
}