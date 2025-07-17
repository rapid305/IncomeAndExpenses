package com.example.incomeexpensesapplication.JetpackComposeUI


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.incomeexpensesapplication.DataBase.Model.Expenses
import com.example.incomeexpensesapplication.DataBase.Model.Income
import com.example.incomeexpensesapplication.ViewModel.GeneralViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyMainScreen(viewModel: GeneralViewModel = viewModel() , NavController: NavController) {


    val records by viewModel.allFinance.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = "Wallet",
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
//                navigationIcon = {
//                    IconButton(onClick = { /* TODO */ }) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                },
//                actions = {
//                    IconButton(onClick = { /* TODO */ }) {
//                        Icon(
//                            imageVector = Icons.Filled.Menu,
//                            contentDescription = "Menu"
//                        )
//                    }
//                },
                scrollBehavior = scrollBehavior
            )
        },

        bottomBar = { BottomAppBarExample(navController = NavController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
                ) {
                RoundedCornerBoxClipped()
            }
            HorizontalDivider(
                color = Color.Gray,
                thickness = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Text(
                text = "Future Expenses and Income",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                textAlign = TextAlign.Center
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(10.dp)
            ) {
                items(records) { record ->
                    when(record){
                        is Income -> IncomeCard(record)
                        is Expenses -> ExpenseCard(record)
                    }
                }
            }
        }
    }
}

/*@Composable
fun ListOfItem(textItem: String) {
    Card(
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = textItem,
            modifier = Modifier.padding(4.dp),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}*/

@Composable
fun ButtonAdd(navController: NavController) {
    FloatingActionButton(
        onClick = {
          navController.navigate("AddWindow")
        },
        modifier = Modifier.padding(15.dp),
        containerColor = Color.Blue,
        contentColor = Color.White,
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add description")
    }
}

@Composable
fun RoundedCornerBoxClipped() {
    Box(
        modifier = Modifier
            .size(width = 300.dp, height = 200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Magenta)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Future Expenses and Income", color = Color.White)
    }
}
@Composable
fun BottomAppBarExample(navController: NavController) {

            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Menu , contentDescription = "Finance")
                    }

                        ButtonAdd(navController = navController)

                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Settings , contentDescription = "Settings")
                    }

                }
            }
}

@Composable
fun IncomeCard(income: Income) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE8F5E9) // Светло-зеленый для доходов
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = income.title, style = MaterialTheme.typography.titleMedium)
            Text(text = "+${income.amount}",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Green))
        }
    }
}

@Composable
fun ExpenseCard(expense: Expenses) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFEBEE) // Светло-красный для расходов
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = expense.title, style = MaterialTheme.typography.titleMedium)
            Text(text = "-${expense.amount}",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Red))
        }
    }
}


