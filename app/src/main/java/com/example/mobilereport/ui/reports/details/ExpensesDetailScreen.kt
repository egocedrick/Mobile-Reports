package com.example.mobilereport.ui.reports.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobilereport.data.MockData
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesDetailScreen(startDate: String, endDate: String, navController: NavController) {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val start = LocalDate.parse(startDate, formatter)
    val end = LocalDate.parse(endDate, formatter)

    val days = daysBetweenInclusive(start, end)

    // Fixed categories
    val categories = listOf("BUS", "Fuel-in", "Washing", "Parking", "Toll Fee")

    // Compute per-day totals per category
    val dailyData = days.map { date ->
        val itemsForDay = MockData.expenses.filter { it.date == date.toString() }
        val grouped = itemsForDay.groupBy { it.category }
        val perCategory = categories.map { cat -> grouped[cat]?.sumOf { it.amount } ?: 0.0 }
        val total = perCategory.sum()
        Triple(date, perCategory, total)
    }

    val grandTotal = dailyData.sumOf { it.third }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Expenses Report Summary") },
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
            // Horizontal scroll in case maraming categories
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .fillMaxWidth()
            ) {
                Column {
                    // Header row
                    Row(Modifier.padding(bottom = 4.dp)) {
                        Text("Date", Modifier.width(140.dp), style = MaterialTheme.typography.titleSmall)
                        categories.forEach { cat ->
                            Text(cat, Modifier.width(100.dp), style = MaterialTheme.typography.titleSmall)
                        }
                        Text("Total", Modifier.width(100.dp), style = MaterialTheme.typography.titleSmall)
                    }
                    Divider()

                    // Body rows
                    LazyColumn {
                        items(dailyData) { (date, perCategory, total) ->
                            Row(
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate("expensesDetailDay/$date/$startDate/$endDate")
                                    }
                                    .padding(vertical = 4.dp)
                            ) {
                                Text(
                                    date.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")),
                                    Modifier.width(140.dp),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                perCategory.forEach { value ->
                                    Text(
                                        if (value == 0.0) "" else "%.2f".format(value),
                                        Modifier.width(100.dp),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                                Text(
                                    "%.2f".format(total),
                                    Modifier.width(100.dp),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            Divider()
                        }
                    }

                    // Grand total row
                    Row(Modifier.padding(vertical = 6.dp)) {
                        Text("GRAND TOTAL", Modifier.width(140.dp), style = MaterialTheme.typography.titleMedium)
                        categories.forEach { cat ->
                            val catTotal = dailyData.sumOf { (d, perCat, _) ->
                                val idx = categories.indexOf(cat)
                                perCat[idx]
                            }
                            Text(
                                "%.2f".format(catTotal),
                                Modifier.width(100.dp),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Text(
                            "%.2f".format(grandTotal),
                            Modifier.width(100.dp),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

private fun daysBetweenInclusive(start: LocalDate, end: LocalDate): List<LocalDate> {
    val count = ChronoUnit.DAYS.between(start, end).toInt()
    return (0..count).map { start.plusDays(it.toLong()) }
}