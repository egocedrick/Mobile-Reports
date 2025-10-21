package com.example.mobilereport.ui.reports

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobilereport.model.InspectorItem

@Composable
fun InspectorRow(item: InspectorItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Date: ${item.date}", style = MaterialTheme.typography.titleMedium)
            Text("Bus: ${item.busNumber}", style = MaterialTheme.typography.bodyMedium)
            Text("Inspector: ${item.inspector}", style = MaterialTheme.typography.bodyMedium)
            Text("Driver: ${item.driver}", style = MaterialTheme.typography.bodySmall)
            Text("Conductor: ${item.conductor}", style = MaterialTheme.typography.bodySmall)
            Spacer(Modifier.height(4.dp))
            Text("Passengers: ${item.numPassengers}", style = MaterialTheme.typography.bodyMedium)
            Text("Km Post: ${item.kmPost}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}