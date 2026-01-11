package com.example.mobilereport.ui.reports.details

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
import com.example.mobilereport.model.RemittanceItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemittanceDetailDayScreen(
    date: String,
    startDate: String,
    endDate: String,
    navController: NavController,
    remittances: List<RemittanceItem>
) {
    val itemsForDay = remittances.filter { it.date == date }

    val totalTrips = itemsForDay.sumOf { it.trips }
    val totalNet = itemsForDay.sumOf { it.netCash }
    val totalIngresso = itemsForDay.sumOf { it.amountRemittance }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DAILY REMITTANCE REPORT") },
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
            Text("Report Range: $startDate to $endDate", style = MaterialTheme.typography.bodyMedium)

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
                        TableHeader("Time")
                        TableHeader("Trips")
                        TableHeader("Net Amount")
                        TableHeader("Ingresso")
                    }

                    Divider()

                    // ✅ BODY
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 400.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        items(itemsForDay) { item ->

                            Row {
                                TableCell(item.busNumber)
                                TableCell(item.time)
                                TableCell(item.trips.toString())
                                TableCell("₱${"%,.2f".format(item.netCash.toDouble())}")
                                TableCell("₱${"%,.2f".format(item.amountRemittance.toDouble())}")
                            }

                            Divider()
                        }
                    }
                }
            }

            Divider(Modifier.padding(vertical = 12.dp))

            // ✅ CLEAN TOTALS
            Text("Total Trips: $totalTrips", style = MaterialTheme.typography.titleMedium)
            Text("Total Net: ₱${"%,.2f".format(totalNet.toDouble())}", style = MaterialTheme.typography.titleMedium)
            Text("Total Ingresso: ₱${"%,.2f".format(totalIngresso.toDouble())}", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
private fun TableHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.width(140.dp)
    )
}

@Composable
private fun TableCell(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.width(140.dp)
    )
}