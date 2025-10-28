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
import com.example.mobilereport.model.DispatcherItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DispatcherDetailDayScreen(
    date: String,
    startDate: String,
    endDate: String,
    navController: NavController
) {
    val itemsForDay = MockData.dispatches.filter { it.date == date }
    val totalDispatch = itemsForDay.count { it.type == "D" }
    val totalReverse = itemsForDay.count { it.type == "R" }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DAILY DISPATCH REPORT") },
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
                        Text("Dispatch", Modifier.width(120.dp), style = MaterialTheme.typography.titleSmall)
                        Text("Conductor", Modifier.width(120.dp), style = MaterialTheme.typography.titleSmall)
                        Text("Driver", Modifier.width(120.dp), style = MaterialTheme.typography.titleSmall)
                    }
                    Divider()

                    // Body rows
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 400.dp), // limit height, scrollable
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        items(itemsForDay) { d: DispatcherItem ->
                            Row {
                                Text(d.busNumber, Modifier.width(120.dp), style = MaterialTheme.typography.bodySmall)
                                Text(d.time, Modifier.width(120.dp), style = MaterialTheme.typography.bodySmall)
                                Text(d.dispatcher, Modifier.width(120.dp), style = MaterialTheme.typography.bodySmall)
                                Text(d.conductor, Modifier.width(120.dp), style = MaterialTheme.typography.bodySmall)
                                Text(d.driver, Modifier.width(120.dp), style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }

            Divider(Modifier.padding(vertical = 8.dp))

            Text(
                "TOTAL Dispatch: $totalDispatch   Reverse: $totalReverse",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}