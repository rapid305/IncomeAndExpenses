package com.example.incomeexpensesapplication.DataBase.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

sealed interface FinancialRecords {
    val id : Int
    val title : String
    val amount : String
//    val date : String
}

@Entity(tableName = "Expenses")
data class Expenses(
    @PrimaryKey(autoGenerate = true) override val id : Int = 0,//По умолчанию 0
//    val categoryExpense : String, // категория трат
    override val title : String, // заголовок трат
    override val amount : String, // кол-во денег
//   override val date : String // даты траты
) : FinancialRecords
@Entity(tableName = "Income")
data class Income(
    @PrimaryKey(autoGenerate = true) override val id : Int = 0,//По умолчанию 0
//    val categoryIncome : String, // категория дохода
    override val title : String, // заголовок дохода
    override val amount : String, // кол-во дохода
//    override val date: String // дата дохода
) : FinancialRecords
