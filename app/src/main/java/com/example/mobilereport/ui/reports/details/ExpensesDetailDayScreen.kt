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
import com.example.mobilereport.model.ExpensesItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesDetailDayScreen(
    date: String,
    startDate: String,
    endDate: String,
    navController: NavController,
    expensesForDay: List<ExpensesItem>
) {
    val totalForDay = expensesForDay.sumOf { it.totalExpenses }

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

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .fillMaxWidth()
            ) {
                Column {

                    // ✅ HEADER
                    Row(Modifier.padding(vertical = 4.dp)) {
                        TableHeader("Bus")
                        TableHeader("Fuel-in")
                        TableHeader("Washing")
                        TableHeader("Parking")
                        TableHeader("Toll Fee")
                        TableHeader("Total")
                    }

                    Divider()

                    // ✅ BODY
                    expensesForDay.forEach { e ->
                        Row(Modifier.padding(vertical = 4.dp)) {
                            TableCell(e.busNumber)
                            TableCell(e.fuelin.toString())
                            TableCell(e.washing.toString())
                            TableCell(e.parking.toString())
                            TableCell(e.tollfee.toString())
                            TableCell("₱${"%,.2f".format(e.totalExpenses)}")
                        }
                        Divider()
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Text(
                "TOTAL Expenses: ₱${"%,.2f".format(totalForDay)}",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun TableHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.width(120.dp)
    )
}

@Composable
private fun TableCell(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.width(120.dp)
    )
}