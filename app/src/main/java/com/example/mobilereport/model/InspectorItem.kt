package com.example.mobilereport.model

data class InspectorItem(
    val requestedRange: String,   // e.g., "2025-10-01 — 2025-10-07"
    val date: String,             // specific date of inspection
    val busNumber: String,        // bus identifier
    val numPassengers: Int,       // passenger count (if applicable)
    val kmPost: Double,           // kilometer post or distance marker
    val inspector: String,        // inspector name
    val driver: String,           // driver name
    val conductor: String,        // conductor name
    val time: String,             // ✅ new: inspection/arrival time (e.g., "10:00 AM")
    val discrepancy: Int          // ✅ new: discrepancy count for this bus
)