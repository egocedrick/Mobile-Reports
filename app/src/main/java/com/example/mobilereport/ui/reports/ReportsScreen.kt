package com.example.mobilereport.ui.reports

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobilereport.ui.reports.comments.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen() {
    var remittanceExpanded by remember { mutableStateOf(true) }   // default open
    var dispatcherExpanded by remember { mutableStateOf(false) }
    var inspectorExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Mobile Reports") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Reports", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(12.dp))

            SectionCard(
                title = "Remittance",
                expanded = remittanceExpanded,
                onToggle = { remittanceExpanded = !remittanceExpanded }
            ) {
                RemittanceSection()
            }

            SectionCard(
                title = "Dispatcher",
                expanded = dispatcherExpanded,
                onToggle = { dispatcherExpanded = !dispatcherExpanded }
            ) {
                DispatcherSection()
            }

            SectionCard(
                title = "Inspector",
                expanded = inspectorExpanded,
                onToggle = { inspectorExpanded = !inspectorExpanded }
            ) {
                InspectorSection()
            }
        }
    }
}