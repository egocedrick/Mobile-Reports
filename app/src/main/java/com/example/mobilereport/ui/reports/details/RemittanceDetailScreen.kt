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
import com.example.mobilereport.ui.reports.RemittanceRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemittanceDetailScreen(startDate: String, endDate: String, navController: NavController) {
    // Filter items within range
    val items = MockData.remittances.filter { it.dateRequested in startDate..endDate }
    val total = items.sumOf { it.amountRemittance }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Remittance Detail — $startDate to $endDate") },
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
                    "Total from $startDate to $endDate: ₱${"%,.2f".format(total)}",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            items(items) { item ->
                RemittanceRow(item)
            }
        }
    }
}