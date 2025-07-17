package com.example.incomeexpensesapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.incomeexpensesapplication.DataBase.AppDatabase
import com.example.incomeexpensesapplication.Navigationn.NavGraphSetup
import com.example.incomeexpensesapplication.Repository.ExpensesRepository
import com.example.incomeexpensesapplication.Repository.IncomeRepository
import com.example.incomeexpensesapplication.ViewModel.GeneralViewModel


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = AppDatabase.getDatabase(this)
//        val repository = ExpensesRepository(db.getDaoExpenses())
        val incomeRepo = IncomeRepository(db.getDaoIncome())
        val expenseRepo = ExpensesRepository(db.getDaoExpenses())
        val viewModel = GeneralViewModel(incomeRepo, expenseRepo)
        setContent {
            NavGraphSetup(viewModel)
        }
    }
}


