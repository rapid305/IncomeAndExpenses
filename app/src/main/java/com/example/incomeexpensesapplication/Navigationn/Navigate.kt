package com.example.incomeexpensesapplication.Navigationn

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.incomeexpensesapplication.JetpackComposeUI.MyMainScreen
import com.example.incomeexpensesapplication.JetpackComposeUI.Window
import com.example.incomeexpensesapplication.ViewModel.GeneralViewModel


@Composable
fun NavGraphSetup(viewModel: GeneralViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "MainWindow", builder = {
        composable("MainWindow"){
            MyMainScreen(viewModel , navController)
        }
        composable("AddWindow"){
            Window(navController , viewModel)
        }
    })
}