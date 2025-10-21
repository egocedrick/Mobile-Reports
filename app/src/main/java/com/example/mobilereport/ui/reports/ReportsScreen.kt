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
    // State for showing DatePicker
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedRoute by remember { mutableStateOf<String?>(null) }

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
                    showDatePicker = true
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Remittance") }

            Button(
                onClick = {
                    selectedRoute = "dispatcherDetail"
                    showDatePicker = true
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Dispatcher") }

            Button(
                onClick = {
                    selectedRoute = "inspectorDetail"
                    showDatePicker = true
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Inspector") }

            Button(
                onClick = {
                    selectedRoute = "expensesDetail"
                    showDatePicker = true
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Expenses") }
        }
    }

    // DatePickerDialog with default = today
    if (showDatePicker && selectedRoute != null) {
        val today = System.currentTimeMillis()
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = today)

        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val millis = datePickerState.selectedDateMillis
                        if (millis != null) {
                            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            val dateString = formatter.format(Date(millis))
                            navController.navigate("${selectedRoute}/$dateString")
                        }
                        showDatePicker = false
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) { Text("Cancel") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}