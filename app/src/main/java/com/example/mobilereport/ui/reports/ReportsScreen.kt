package com.example.mobilereport.ui.reports

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(
    isDarkMode: Boolean,
    onToggleTheme: () -> Unit,
    navController: NavController
) {
    var showStartPicker by remember { mutableStateOf(false) }
    var showEndPicker by remember { mutableStateOf(false) }
    var selectedRoute by remember { mutableStateOf<String?>(null) }
    var startDate by remember { mutableStateOf<String?>(null) }
    var endDate by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Mobile Reports",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                actions = {
                    IconButton(onClick = onToggleTheme) {
                        Icon(
                            imageVector = if (isDarkMode) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                            contentDescription = "Toggle Theme"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                "DASHBOARD",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    selectedRoute = "remittanceDetail"
                    showStartPicker = true
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Remittance") }

            Button(
                onClick = {
                    selectedRoute = "dispatcherDetail"
                    showStartPicker = true
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Dispatcher") }

            Button(
                onClick = {
                    selectedRoute = "inspectorDetail"
                    showStartPicker = true
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Inspector") }

            Button(
                onClick = {
                    selectedRoute = "expensesDetail"
                    showStartPicker = true
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Expenses") }
        }
    }

    // Start Date Picker
    if (showStartPicker && selectedRoute != null) {
        val today = System.currentTimeMillis()
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = today)

        DatePickerDialog(
            onDismissRequest = { showStartPicker = false },
            confirmButton = {
                TextButton(onClick = {
                    val millis = datePickerState.selectedDateMillis
                    if (millis != null) {
                        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        startDate = formatter.format(Date(millis))
                        showStartPicker = false
                        showEndPicker = true // proceed to end date
                    }
                }) { Text("Next") }
            },
            dismissButton = {
                TextButton(onClick = { showStartPicker = false }) { Text("Cancel") }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    "Select Start Date",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                DatePicker(state = datePickerState)
            }
        }
    }

    // End Date Picker
    if (showEndPicker && selectedRoute != null && startDate != null) {
        val today = System.currentTimeMillis()
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = today)

        DatePickerDialog(
            onDismissRequest = { showEndPicker = false },
            confirmButton = {
                TextButton(onClick = {
                    val millis = datePickerState.selectedDateMillis
                    if (millis != null) {
                        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        endDate = formatter.format(Date(millis))
                        navController.navigate("${selectedRoute}/${startDate}/${endDate}")
                    }
                    showEndPicker = false
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showEndPicker = false }) { Text("Cancel") }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    "Select End Date",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                DatePicker(state = datePickerState)
            }
        }
    }
}