package com.example.mobilereport.model

data class ExpensesItem(
    val date: String,
    val busNumber: String,
    val fuelin: Int,
    val washing: Int,
    val parking: Int,
    val tollfee: Int,
    val totalExpenses: Double
)