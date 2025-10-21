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
import com.example.mobilereport.model.DispatcherItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DispatcherDetailScreen(startDate: String, endDate: String, navController: NavController) {
    // Filter items within range
    val items = MockData.dispatches.filter { it.date in startDate..endDate }
    val totalDispatch = items.count { it.type == "D" }
    val totalReverse = items.count { it.type == "R" }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dispatcher Detail — $startDate to $endDate") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    "Dispatch: $totalDispatch   Reverse: $totalReverse",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            items(items) { d: DispatcherItem ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("${d.type} • ${d.busNumber}", style = MaterialTheme.typography.titleSmall)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Dispatcher: ${d.dispatcher}", style = MaterialTheme.typography.bodySmall)
                        Text("Driver: ${d.driver}  Conductor: ${d.conductor}", style = MaterialTheme.typography.bodySmall)
                        Text("From: ${d.dispatchFrom}  To: ${d.dispatchTo}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}