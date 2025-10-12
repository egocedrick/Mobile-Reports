package com.example.mobilereport.model

data class InspectorItem(
    val requestedRange: String,  // e.g., "2025-10-01 — 2025-10-07"
    val date: String,
    val busNumber: String,
    val numPassengers: Int,
    val kmPost: Double,
    val inspector: String,
    val driver: String,
    val conductor: String
)