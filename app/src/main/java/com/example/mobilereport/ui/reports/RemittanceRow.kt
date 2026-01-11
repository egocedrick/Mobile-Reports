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

            Text("Bus: ${item.busNumber}", style = MaterialTheme.typography.titleMedium)

            Spacer(Modifier.height(4.dp))
            Text("Time: ${item.time}", style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(4.dp))
            Text("Trips: ${item.trips}", style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(4.dp))
            Text("Net: ₱${"%,.2f".format(item.netCash.toDouble())}", style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(4.dp))
            Text("Ingresso: ₱${"%,.2f".format(item.amountRemittance.toDouble())}", style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(8.dp))
            Text(
                "Total Remitted: ₱${"%,.2f".format(item.amountRemittance.toDouble())}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}