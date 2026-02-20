package com.example.mobilereport.model

data class InspectorItem(
    val date: String,
    val inspector: String,
    val busNumber: String,
    val time: String,
    val kmPost: Int,
    val remainingPassenger: Int,
    val numPassengers: Int,
    val discrepancy: Int,
    val driver: String,
    val conductor: String
)
