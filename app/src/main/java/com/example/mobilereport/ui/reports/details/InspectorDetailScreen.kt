package com.example.mobilereport.ui.reports.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InspectorDetailScreen(startDate: String, endDate: String, navController: NavController) {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val start = LocalDate.parse(startDate, formatter)
    val end = LocalDate.parse(endDate, formatter)

    val days = daysBetweenInclusive(start, end)

    // compute totals per day
    val dailyTotals = days.map { date ->
        val itemsForDay = MockData.inspectors.filter { it.date == date.toString() }
        val totalForDay = itemsForDay.size // bilang ng bus inspected that day
        date to totalForDay
    }

    val grandTotal = dailyTotals.sumOf { it.second }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inspector Report Summary") },
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
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header row
            Row(Modifier.fillMaxWidth()) {
                Text("Date", Modifier.weight(1f), style = MaterialTheme.typography.titleMedium)
                Text("Buses Inspected", Modifier.weight(1f), style = MaterialTheme.typography.titleMedium)
            }
            Divider()

            // Body rows
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(dailyTotals) { (date, total) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("inspectorNames/$date/$startDate/$endDate")
                            }
                            .padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = date.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")),
                            Modifier.weight(1f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = total.toString(),
                            Modifier.weight(1f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            Divider()

            // Grand total
            Text(
                text = "TOTAL Buses Inspected: $grandTotal",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

private fun daysBetweenInclusive(start: LocalDate, end: LocalDate): List<LocalDate> {
    val count = ChronoUnit.DAYS.between(start, end).toInt()
    return (0..count).map { start.plusDays(it.toLong()) }
}