package com.example.mobilereport.model

data class ExpenseItem(
    val date: String,          // e.g. "2025-10-07"
    val category: String,      // e.g. "Fuel", "Maintenance"
    val amount: Double,        // e.g. 3200.0
    val notes: String? = null  // optional remarks
)