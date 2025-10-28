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
fun RemittanceDetailDayScreen(
    date: String,
    startDate: String,
    endDate: String,
    navController: NavController
) {
    val itemsForDay = MockData.remittances.filter { it.dateRequested == date }
    val totalForDay = itemsForDay.sumOf { it.amountRemittance }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Remittance — $date") },
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
            // Inclusive range
            Text("Report Range: $startDate to $endDate", style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(8.dp))

            // Total for this day
            Text(
                "Total Remittance: ₱${"%,.2f".format(totalForDay)}",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(Modifier.height(12.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(itemsForDay) { item ->
                    RemittanceRow(item)
                }
            }
        }
    }
}