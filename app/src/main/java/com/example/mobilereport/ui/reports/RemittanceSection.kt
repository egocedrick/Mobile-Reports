package com.example.mobilereport.ui.reports

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobilereport.data.MockData
import com.example.mobilereport.model.RemittanceItem

@Composable
fun RemittanceSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text("Daily Remittance Summary", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))

        val total = MockData.remittances.sumOf { it.amountRemittance }
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("Total Amount Remitted: ₱${"%,.2f".format(total)}", style = MaterialTheme.typography.bodyLarge)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(MockData.remittances) { item: RemittanceItem ->
                RemittanceRow(item)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun RemittanceRow(item: RemittanceItem) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("${item.company} • ${item.vehicle} • ${item.route}", style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                "Gross: ₱${"%,.2f".format(item.grossIncome)}  Net: ₱${"%,.2f".format(item.netCash)}  Remitted: ₱${"%,.2f".format(item.amountRemittance)}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text("Trips: ${item.trips}  KM: ${item.kmRun}  Liters Out: ${item.litersOut}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(6.dp))
            Text("Driver: ${item.driver}  Conductor: ${item.conductor}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(6.dp))
            Text("PAX A/S/St: ${item.paxAdult}/${item.paxSenior}/${item.paxStudent}  Baggage: ${item.baggageCount}", style = MaterialTheme.typography.bodySmall)
        }
    }
}