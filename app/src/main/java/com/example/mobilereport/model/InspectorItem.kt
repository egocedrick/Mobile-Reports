package com.example.mobilereport.model

data class InspectorItem(
    val requestedRange: String,
    val date: String,
    val busNumber: String,
    val numPassengers: Int,       // Actual Count
    val kmPost: Double,           // KM
    val inspector: String,
    val driver: String,
    val conductor: String,
    val time: String,
    val discrepancy: Int,
    val remainingPassenger: Int = 0 // ➕ new field
)