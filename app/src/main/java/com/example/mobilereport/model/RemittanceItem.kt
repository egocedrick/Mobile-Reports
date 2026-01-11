package com.example.mobilereport.model

data class RemittanceItem(
    val date: String,
    val busNumber: String,
    val time: String,
    val trips: Int,
    val netCash: Int,
    val amountRemittance: Int
)