package com.example.mobilereport.ui.reports.details

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobilereport.data.MockData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesDetailDayScreen(
    date: String,
    startDate: String,
    endDate: String,
    navController: NavController
) {
    val expensesForDay = MockData.expenses.filter { it.date == date }
    val grouped = expensesForDay.groupBy { it.category }
    val totalForDay = expensesForDay.filter { it.category != "BUS" }.sumOf { it.amount }

    // Fixed categories in order
    val categories = listOf("BUS", "Fuel-in", "Washing", "Parking", "Toll Fee")

    // Find max number of entries across categories
    val maxEntries = categories.maxOf { grouped[it]?.size ?: 0 }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("EXPENSES REPORT") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Date: $date", style = MaterialTheme.typography.titleMedium)
            Text("Report Range: $startDate to $endDate", style = MaterialTheme.typography.bodySmall)
            Spacer(Modifier.height(12.dp))

            // Horizontal scroll in case maraming categories
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .fillMaxWidth()
            ) {
                Column {
                    // Header row
                    Row(Modifier.padding(bottom = 4.dp)) {
                        categories.forEach { category ->
                            Text(
                                category,
                                Modifier.width(100.dp),
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                    }
                    Divider()

                    // Data rows
                    for (i in 0 until maxEntries) {
                        Row(Modifier.padding(vertical = 2.dp)) {
                            categories.forEach { category ->
                                val cellText = if (category == "BUS") {
                                    // Show bus number as string
                                    grouped[category]?.getOrNull(i)?.amount?.toInt()?.toString() ?: ""
                                } else {
                                    grouped[category]?.getOrNull(i)?.amount?.let { "%.2f".format(it) } ?: ""
                                }
                                Text(
                                    cellText,
                                    Modifier.width(100.dp),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                        Divider()
                    }

                    // Totals row (skip BUS)
                    Row(Modifier.padding(vertical = 4.dp)) {
                        categories.forEach { category ->
                            if (category == "BUS") {
                                Text("", Modifier.width(100.dp))
                            } else {
                                val total = grouped[category]?.sumOf { it.amount } ?: 0.0
                                Text(
                                    "%.2f".format(total),
                                    Modifier.width(100.dp),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            // Total for the day (excluding BUS)
            Text(
                "TOTAL Expenses: %.2f".format(totalForDay),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}