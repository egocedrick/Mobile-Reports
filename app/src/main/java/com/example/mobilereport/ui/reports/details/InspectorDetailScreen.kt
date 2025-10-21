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
fun InspectorDetailScreen(date: String, navController: NavController) {
    val items = MockData.inspectors.filter { it.date == date }
    val totalPassengers = items.sumOf { it.numPassengers }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inspector Detail — $date") },
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
                    "Passengers: $totalPassengers",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            items(items) { i: InspectorItem ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("${i.busNumber} — ${i.numPassengers} pax", style = MaterialTheme.typography.titleSmall)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Inspector: ${i.inspector}", style = MaterialTheme.typography.bodySmall)
                        Text("Driver: ${i.driver}  Conductor: ${i.conductor}", style = MaterialTheme.typography.bodySmall)
                        Text("KM Post: ${i.kmPost}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}