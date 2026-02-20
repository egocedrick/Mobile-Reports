package com.example.mobilereport.ui.reports

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Composable
fun ExpensesSection(
    dateRange: Pair<LocalDate, LocalDate>?,
    onDayClick: (LocalDate) -> Unit
) {
    if (dateRange == null) {
        Text("Select a date range to view expenses.", style = MaterialTheme.typography.bodyMedium)
        return
    }

    val (start, end) = dateRange
    val days = daysBetweenInclusive(start, end)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Expenses from $start to $end",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(days) { date ->
                val mockTotal = mockTotalFor(date)

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDayClick(date) }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = date.toString(),
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Total expenses",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        Text(
                            text = "₱${"%,.2f".format(mockTotal.toDouble())}",
                            style = MaterialTheme.typography.titleMedium
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

private fun mockTotalFor(date: LocalDate): Int {
    val base = (date.dayOfMonth * 37) % 500
    return 100 + base
}