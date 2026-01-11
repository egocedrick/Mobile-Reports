package com.example.mobilereport.model

data class DispatcherItem(
    val date: String,          // "yyyy-MM-dd"
    val busNumber: String,
    val time: String,
    val dispatcher: String,    // "D" or "R"
    val conductor: String,
    val driver: String
)
