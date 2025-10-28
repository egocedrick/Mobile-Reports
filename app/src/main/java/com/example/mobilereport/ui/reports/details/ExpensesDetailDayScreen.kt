package com.example.mobilereport.ui.reports.details

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
    val totalForDay = expensesForDay.sumOf { it.amount }

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
                    // Header row (categories)
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

                    // Data rows (entries per category)
                    for (i in 0 until maxEntries) {
                        Row(Modifier.padding(vertical = 2.dp)) {
                            categories.forEach { category ->
                                val value = grouped[category]?.getOrNull(i)?.amount?.toString() ?: ""
                                Text(
                                    value,
                                    Modifier.width(100.dp),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                        Divider()
                    }

                    // Totals row
                    Row(Modifier.padding(vertical = 4.dp)) {
                        categories.forEach { category ->
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

            Spacer(Modifier.height(12.dp))

            // Total for the day
            Text(
                "TOTAL Expenses: %.2f".format(totalForDay),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}