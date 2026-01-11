package com.example.mobilereport.ui.reports

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobilereport.model.ExpensesItem

@Composable
fun ExpensesRow(item: ExpensesItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text("Bus: ${item.busNumber}", style = MaterialTheme.typography.titleMedium)

            Spacer(Modifier.height(4.dp))
            Text("Fuel-in: ${item.fuelin}", style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(4.dp))
            Text("Washing: ${item.washing}", style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(4.dp))
            Text("Parking: ${item.parking}", style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(4.dp))
            Text("Toll Fee: ${item.tollfee}", style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(8.dp))
            Text(
                "Total: ₱${"%,.2f".format(item.totalExpenses)}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}