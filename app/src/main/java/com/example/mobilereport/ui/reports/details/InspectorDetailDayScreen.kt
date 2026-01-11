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
import com.example.mobilereport.model.InspectorItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InspectorDetailDayScreen(
    inspectorName: String,
    date: String,
    startDate: String,
    endDate: String,
    navController: NavController,
    inspections: List<InspectorItem> // ✅ pass real data here
) {
    val itemsForInspector = inspections.filter {
        it.date == date && it.inspector == inspectorName
    }

    val totalDiscrepancies = itemsForInspector.sumOf { it.discrepancy }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DAILY INSPECTOR REPORT") },
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
            Text("Inspector: $inspectorName", style = MaterialTheme.typography.bodyMedium)
            Text("Report Range: $startDate to $endDate", style = MaterialTheme.typography.bodySmall)
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
                        Text("BUS", Modifier.width(100.dp), style = MaterialTheme.typography.titleSmall)
                        Text("Time", Modifier.width(100.dp), style = MaterialTheme.typography.titleSmall)
                        Text("KM", Modifier.width(100.dp), style = MaterialTheme.typography.titleSmall)
                        Text("Remaining Passenger", Modifier.width(160.dp), style = MaterialTheme.typography.titleSmall)
                        Text("Actual Count", Modifier.width(120.dp), style = MaterialTheme.typography.titleSmall)
                        Text("Discrepancy", Modifier.width(120.dp), style = MaterialTheme.typography.titleSmall)
                    }
                    Divider()

                    // Body rows
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 400.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        items(itemsForInspector) { item: InspectorItem ->
                            Row {
                                Text(item.busNumber, Modifier.width(100.dp), style = MaterialTheme.typography.bodySmall)
                                Text(item.time, Modifier.width(100.dp), style = MaterialTheme.typography.bodySmall)
                                Text(item.kmPost.toString(), Modifier.width(100.dp), style = MaterialTheme.typography.bodySmall)
                                Text(item.remainingPassenger.toString(), Modifier.width(160.dp), style = MaterialTheme.typography.bodySmall)
                                Text(item.numPassengers.toString(), Modifier.width(120.dp), style = MaterialTheme.typography.bodySmall)
                                Text(item.discrepancy.toString(), Modifier.width(120.dp), style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }

            Divider(Modifier.padding(vertical = 8.dp))

            Text(
                "TOTAL Discrepancies: $totalDiscrepancies",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}