package com.example.mobilereport.model

data class RemittanceItem(
    val dateRequested: String,
    val company: String,
    val partialCommission: Double,
    val vehicle: String,
    val grossIncome: Double,
    val dateDispatched: String,
    val timeDispatched: String,   // ➕ bagong field para sa oras
    val dateRemitted: String,
    val route: String,
    val driver: String,
    val conductor: String,
    val trips: Int,
    val kmRun: Double,
    val litersOut: Double,
    val loanValeDamages: Double,
    val driverShare: Double,
    val conductorShare: Double,
    val others: Double,
    val netCash: Double,
    val amountRemittance: Double,
    val overShort: Double,
    val paxAdult: Int,
    val paxSenior: Int,
    val paxStudent: Int,
    val baggageCount: Int
)