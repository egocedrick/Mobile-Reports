package com.example.mobilereport.ui.reports

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobilereport.model.RemittanceItem

@Composable
fun RemittanceRow(item: RemittanceItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Company: ${item.company}", style = MaterialTheme.typography.titleMedium)
            Text("Vehicle: ${item.vehicle}", style = MaterialTheme.typography.bodyMedium)
            Text("Route: ${item.route}", style = MaterialTheme.typography.bodyMedium)
            Text("Driver: ${item.driver}", style = MaterialTheme.typography.bodySmall)
            Text("Conductor: ${item.conductor}", style = MaterialTheme.typography.bodySmall)
            Spacer(Modifier.height(4.dp))
            Text(
                "Amount Remitted: ₱${"%,.2f".format(item.amountRemittance)}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}