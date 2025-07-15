package com.example.incomeexpensesapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.incomeexpensesapplication.DataBase.AppDatabase
import com.example.incomeexpensesapplication.DataBase.Navigation.NavGraphSetup
import com.example.incomeexpensesapplication.Repository.ExpensesRepository
import com.example.incomeexpensesapplication.ViewModel.ExpensesViewModel
import com.example.incomeexpensesapplication.UI_.MyMainScreen

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = AppDatabase.getDatabase(this)
        val repository = ExpensesRepository(db.getDaoExpenses())
        val viewModel = ExpensesViewModel(repository)
        setContent {
            NavGraphSetup(viewModel)
        }
    }
}


