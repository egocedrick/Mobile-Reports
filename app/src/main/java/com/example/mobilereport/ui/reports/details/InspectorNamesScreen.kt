package com.example.mobilereport.ui.reports.details

import androidx.compose.foundation.clickable
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
import com.example.mobilereport.model.InspectorItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InspectorNamesScreen(
    date: String,
    startDate: String,
    endDate: String,
    navController: NavController,
    inspectors: List<InspectorItem>
) {
    val inspectorsForDay = inspectors.filter { it.date == date }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inspectors on $date") },
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
            if (inspectorsForDay.isEmpty()) {
                Text("No inspectors found for this date.")
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(inspectorsForDay.distinctBy { it.inspector }) { inspector ->
                        Text(
                            text = inspector.inspector,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(
                                        "inspectorDetailDay/${inspector.inspector}/$date/$startDate/$endDate"
                                    )
                                }
                                .padding(8.dp)
                        )
                        Divider()
                    }
                }
            }
        }
    }
}