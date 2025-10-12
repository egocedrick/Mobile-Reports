package com.example.mobilereport.ui.reports

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobilereport.ui.reports.comments.SectionWithDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(
    isDarkMode: Boolean,
    onToggleTheme: () -> Unit
) {
    var remittanceExpanded by remember { mutableStateOf(true) }
    var dispatcherExpanded by remember { mutableStateOf(false) }
    var inspectorExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mobile Reports") },
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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "Reports",
                style = MaterialTheme.typography.headlineMedium
            )

            // Remittance Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                SectionWithDate(
                    title = "Remittance",
                    expanded = remittanceExpanded,
                    onToggle = { remittanceExpanded = !remittanceExpanded }
                ) {
                    RemittanceSection()
                }
            }

            // Dispatcher Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                SectionWithDate(
                    title = "Dispatcher",
                    expanded = dispatcherExpanded,
                    onToggle = { dispatcherExpanded = !dispatcherExpanded }
                ) {
                    DispatcherSection()
                }
            }

            // Inspector Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                SectionWithDate(
                    title = "Inspector",
                    expanded = inspectorExpanded,
                    onToggle = { inspectorExpanded = !inspectorExpanded }
                ) {
                    InspectorSection()
                }
            }
        }
    }
}