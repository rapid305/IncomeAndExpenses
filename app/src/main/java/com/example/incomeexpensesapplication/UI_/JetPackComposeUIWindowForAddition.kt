package com.example.incomeexpensesapplication.UI_


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.graphics.Color
import com.example.incomeexpensesapplication.DataBase.Model.Expenses
import com.example.incomeexpensesapplication.DataBase.Model.Income
import com.example.incomeexpensesapplication.ViewModel.GeneralViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Window(navController: NavController , viewModel: GeneralViewModel) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var selectedChoice by remember {
        mutableStateOf("Income")
    }

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
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("MainWindow") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.padding(15.dp)) {
                DropDown { type ->
                    selectedChoice = type
                }
            }
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Input Title") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Input Amount") },
                modifier = Modifier.fillMaxWidth()
            )

            FloatingActionButton(
                modifier = Modifier
                    .width(200.dp)
                    .padding(35.dp),
                onClick = {
                    if(!title.isEmpty() && !amount.isEmpty()) {
                        when (selectedChoice) {
                            "Income" -> viewModel.addIncome(Income(title = title, amount = amount))
                            "Expenses" -> viewModel.addExpense(Expenses(title = title, amount = amount))

                        }
                        Log.d("Created" , "Createddd" )
                    }
                    Log.d("Back" , "CreatedBeforePopBack")
                    navController.popBackStack()
                }
            ) {
                Text(text = "Submit")
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown(onSelectionChanged : (String) -> Unit) {
    val listOfChoice = listOf("Income", "Expenses")
    var isExpanded by remember { mutableStateOf(false) }
    var selectedChoice by remember { mutableStateOf(listOfChoice[0]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }
        ) {
            TextField(
                modifier = Modifier,
                value = selectedChoice,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                listOfChoice.forEach { text ->
                    DropdownMenuItem(
                        text = { Text(text = text) },
                        onClick = {
                            selectedChoice = text
                            isExpanded = false
                            onSelectionChanged(text)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}




