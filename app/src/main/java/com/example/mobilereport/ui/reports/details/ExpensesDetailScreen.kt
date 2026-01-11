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
import com.example.mobilereport.model.ExpensesItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesDetailScreen(
    startDate: String,
    endDate: String,
    navController: NavController,
    expenses: List<ExpensesItem>
) {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val start = LocalDate.parse(startDate, formatter)
    val end = LocalDate.parse(endDate, formatter)

    val days = daysBetweenInclusive(start, end)

    val dailyTotals = days.map { date ->
        val itemsForDay = expenses.filter { it.date == date.toString() }
        val total = itemsForDay.sumOf { it.totalExpenses }
        date to total
    }

    val grandTotal = dailyTotals.sumOf { it.second }

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
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Date", style = MaterialTheme.typography.titleMedium)
                Text("Total Expenses", style = MaterialTheme.typography.titleMedium)
            }

            Divider()

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(dailyTotals) { (date, total) ->

                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(
                                        "expensesDetailDay/$date/$startDate/$endDate"
                                    )
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
                        Divider()
                    }
                }
            }

            Text(
                text = "TOTAL Expenses: ₱${"%,.2f".format(grandTotal)}",
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