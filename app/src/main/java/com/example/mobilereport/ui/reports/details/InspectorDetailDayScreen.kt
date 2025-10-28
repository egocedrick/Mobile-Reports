package com.example.mobilereport.ui.reports.details

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
import com.example.mobilereport.model.InspectorItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InspectorDetailDayScreen(
    inspectorName: String,
    date: String,
    startDate: String,
    endDate: String,
    navController: NavController
) {
    val itemsForInspector = MockData.inspections.filter {
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
            // Header info
            Text("Date: $date", style = MaterialTheme.typography.titleMedium)
            Text("Inspector: $inspectorName", style = MaterialTheme.typography.bodyMedium)
            Text("Report Range: $startDate to $endDate", style = MaterialTheme.typography.bodySmall)
            Spacer(Modifier.height(12.dp))

            // Table header
            Row(Modifier.fillMaxWidth()) {
                Text("BUS", Modifier.weight(1f), style = MaterialTheme.typography.titleSmall)
                Text("Time", Modifier.weight(1f), style = MaterialTheme.typography.titleSmall)
                Text("Discrepancy", Modifier.weight(1f), style = MaterialTheme.typography.titleSmall)
            }
            Divider()

            // Table body
            LazyColumn(
                modifier = Modifier.weight(1f, fill = false),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                items(itemsForInspector) { item: InspectorItem ->
                    Row(Modifier.fillMaxWidth()) {
                        Text(item.busNumber, Modifier.weight(1f), style = MaterialTheme.typography.bodySmall)
                        Text(item.time, Modifier.weight(1f), style = MaterialTheme.typography.bodySmall)
                        Text(item.discrepancy.toString(), Modifier.weight(1f), style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

            Divider(Modifier.padding(vertical = 8.dp))

            // Totals
            Text(
                "TOTAL Discrepancies: $totalDiscrepancies",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}