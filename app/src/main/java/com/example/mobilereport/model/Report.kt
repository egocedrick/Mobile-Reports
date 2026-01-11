package com.example.mobilereport.model

data class Report(
    val id: Int,
    val companyId: Int,
    val amount: Double,
    val dateCreated: String
)