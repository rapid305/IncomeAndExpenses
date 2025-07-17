package com.example.incomeexpensesapplication.JetpackComposeUI



import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
            /*horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center*/
        ) {
            TypeSelectorWithChoiceDisplay(
                onIncomeSelected = { selectedChoice = "Income" },
                onExpenseSelected = { selectedChoice = "Expenses" }
            )
            Box(modifier = Modifier.padding(50.dp)) {
                DropDown { type ->
                    selectedChoice = type
                }
            }
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Input Title") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
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
    val incomeCategories = listOf("Salary", "Freelance", "Investments", "Rent", "Gifts", "Interest", "Sales", "Other")
    val expenseCategories = listOf("Food", "Transport", "Housing", "Health", "Clothing", "Entertainment", "Communication", "Education", "Travel", "Electronics", "Debts", "Other")
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
@Composable
fun TypeSelectorWithChoiceDisplay(
    onIncomeSelected: () -> Unit,
    onExpenseSelected: () -> Unit
) {
    var selectedType by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Кнопки выбора
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Income кнопка
            OutlinedButton(
                onClick = {
                    onIncomeSelected()
                    selectedType = "Income"
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFF4CAF50),
                    containerColor = if (selectedType == "Income") Color(0xFFE8F5E9) else Color.White
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (selectedType == "Income") Color(0xFF4CAF50) else Color.Gray
                )
            ) {
                Text("Income")
            }

            // Expense кнопка
            OutlinedButton(
                onClick = {
                    onExpenseSelected()
                    selectedType = "Expense"
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFFF44336),
                    containerColor = if (selectedType == "Expense") Color(0xFFFFEBEE) else Color.White
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (selectedType == "Expense") Color(0xFFF44336) else Color.Gray
                )
            ) {
                Text("Expense")
            }
        }


        Text(
            text = if (selectedType.isNotEmpty()) "Selected: $selectedType" else "Select type",
            modifier = Modifier.padding(top = 16.dp),
            color = when {
                selectedType.isEmpty() -> Color.Gray
                selectedType == "Income" -> Color(0xFF4CAF50)
                else -> Color(0xFFF44336)
            },
            style = MaterialTheme.typography.bodyLarge
        )
    }
}





