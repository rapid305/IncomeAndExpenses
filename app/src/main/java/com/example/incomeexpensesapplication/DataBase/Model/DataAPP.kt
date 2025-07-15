package com.example.incomeexpensesapplication.DataBase.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Expenses")
data class Expenses(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,//По умолчанию 0
//    val category : String, // категория трат
    val title : String, // заголовок трат
    val amount : String, // кол-во денег
//    val date : String // даты траты
)
@Entity(tableName = "Income")
data class Income(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,//По умолчанию 0
//    val category : String, // категория дохода
    val title : String, // заголовок дохода
    val amount : String, // кол-во дохода
//    val date: String // дата дохода
)
