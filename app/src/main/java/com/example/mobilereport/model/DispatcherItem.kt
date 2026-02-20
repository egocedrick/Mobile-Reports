package com.example.mobilereport.model

data class DispatcherItem(
    val date: String,
    val busNumber: String,
    val time: String,
    val dispatcher: String,
    val conductor: String,
    val driver: String
)
