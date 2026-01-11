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
import com.example.mobilereport.model.DispatcherItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DispatcherDetailDayScreen(
    date: String,
    startDate: String,
    endDate: String,
    navController: NavController,
    itemsForDay: List<DispatcherItem>
) {
    val totalDispatch = itemsForDay.count { it.dispatcher == "D" }
    val totalReverse = itemsForDay.count { it.dispatcher == "R" }

    val scrollState = rememberScrollState()

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

            // ✅ TABLE WITH HORIZONTAL SCROLL
            Row(
                modifier = Modifier
                    .horizontalScroll(scrollState)
                    .fillMaxWidth()
            ) {
                Column {

                    // ✅ HEADER
                    Row(Modifier.padding(vertical = 4.dp)) {
                        TableHeader("BUS")
                        TableHeader("Time")
                        TableHeader("Dispatch")
                        TableHeader("Conductor")
                        TableHeader("Driver")
                    }

                    Divider()

                    // ✅ BODY ROWS
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 400.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        items(itemsForDay) { d ->

                            Row {
                                TableCell(d.busNumber)
                                TableCell(d.time)
                                TableCell(d.dispatcher)
                                TableCell(d.conductor)
                                TableCell(d.driver)
                            }

                            Divider()
                        }
                    }
                }
            }

            Divider(Modifier.padding(vertical = 12.dp))

            // ✅ CLEAN TOTALS
            Text("TOTAL Dispatch: $totalDispatch", style = MaterialTheme.typography.titleMedium)
            Text("TOTAL Reverse: $totalReverse", style = MaterialTheme.typography.titleMedium)
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