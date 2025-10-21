package com.example.mobilereport.ui.reports

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobilereport.model.ExpenseItem

@Composable
fun ExpensesRow(item: ExpenseItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Category: ${item.category}", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(4.dp))
            Text(
                "Amount: ₱${"%,.2f".format(item.amount)}",
                style = MaterialTheme.typography.bodyLarge
            )
            item.notes?.let {
                Spacer(Modifier.height(4.dp))
                Text("Notes: $it", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}