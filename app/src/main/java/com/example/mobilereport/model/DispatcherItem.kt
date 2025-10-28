package com.example.mobilereport.model

data class DispatcherItem(
    val requestedDate: String,
    val type: String,            // "D" or "R"
    val date: String,
    val busNumber: String,
    val dispatcher: String,
    val driver: String,
    val conductor: String,
    val dispatchTo: String,
    val dispatchFrom: String,
    val time: String
)