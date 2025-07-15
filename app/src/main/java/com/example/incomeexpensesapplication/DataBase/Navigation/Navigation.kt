package com.example.incomeexpensesapplication.DataBase.Navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.incomeexpensesapplication.UI_.MyMainScreen
import com.example.incomeexpensesapplication.UI_.Window
import com.example.incomeexpensesapplication.ViewModel.ExpensesViewModel


@Composable
fun NavGraphSetup(viewModel: ExpensesViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "MainWindow", builder = {
      composable("MainWindow"){
          MyMainScreen(viewModel , navController)
      }
        composable("AddWindow"){
            Window()
        }
    })
}