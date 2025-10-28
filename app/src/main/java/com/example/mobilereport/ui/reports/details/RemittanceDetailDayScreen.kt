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
import com.example.mobilereport.data.MockData
import com.example.mobilereport.model.RemittanceItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemittanceDetailDayScreen(
    date: String,
    startDate: String,
    endDate: String,
    navController: NavController
) {
    val itemsForDay = MockData.remittances.filter { it.dateRequested == date }

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

            // ✅ Horizontal scroll wrapper
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .fillMaxWidth()
            ) {
                Column {
                    // Header row
                    Row {
                        Text("BUS", Modifier.width(120.dp), style = MaterialTheme.typography.titleSmall)
                        Text("Time", Modifier.width(120.dp), style = MaterialTheme.typography.titleSmall)
                        Text("No. of Trip", Modifier.width(120.dp), style = MaterialTheme.typography.titleSmall)
                        Text("Net Amount", Modifier.width(140.dp), style = MaterialTheme.typography.titleSmall)
                        Text("Ingresso Amount", Modifier.width(160.dp), style = MaterialTheme.typography.titleSmall)
                    }
                    Divider()

                    // Body rows
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 400.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        items(itemsForDay) { item: RemittanceItem ->
                            Row {
                                Text(item.vehicle, Modifier.width(120.dp), style = MaterialTheme.typography.bodySmall)
                                Text(item.dateDispatched, Modifier.width(120.dp), style = MaterialTheme.typography.bodySmall)
                                Text(item.trips.toString(), Modifier.width(120.dp), style = MaterialTheme.typography.bodySmall)
                                Text("₱${"%,.2f".format(item.netCash)}", Modifier.width(140.dp), style = MaterialTheme.typography.bodySmall)
                                Text("₱${"%,.2f".format(item.amountRemittance)}", Modifier.width(160.dp), style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }

            Divider(Modifier.padding(vertical = 8.dp))

            // Totals
            Text(
                "TOTAL Trips: $totalTrips   Net: ₱${"%,.2f".format(totalNet)}   Ingresso: ₱${"%,.2f".format(totalIngresso)}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}