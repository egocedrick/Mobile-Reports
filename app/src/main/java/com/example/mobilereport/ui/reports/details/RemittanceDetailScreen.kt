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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobilereport.data.MockData
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemittanceDetailScreen(startDate: String, endDate: String, navController: NavController) {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val start = LocalDate.parse(startDate, formatter)
    val end = LocalDate.parse(endDate, formatter)

    val days = daysBetweenInclusive(start, end)

    // Compute totals per day
    val dailyTotals = days.map { date ->
        val itemsForDay = MockData.remittances.filter { it.dateRequested == date.toString() }
        val totalForDay = itemsForDay.sumOf { it.amountRemittance }
        date to totalForDay
    }

    val grandTotal = dailyTotals.sumOf { it.second }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Remittance Report Summary") },
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Date", style = MaterialTheme.typography.titleMedium)
                Text("Amount", style = MaterialTheme.typography.titleMedium)
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
                                // ✅ Pass all three arguments
                                navController.navigate("remittanceDetailDay/${date}/${startDate}/${endDate}")
                            }
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = date.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "₱${"%,.2f".format(total)}",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.End
                        )
                    }
                }
            }

            Divider()

            // Grand total
            Text(
                text = "TOTAL: ₱${"%,.2f".format(grandTotal)}",
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