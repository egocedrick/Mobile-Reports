package com.example.mobilereport.ui.reports.comments

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobilereport.ui.reports.util.DateRangePickerSection

@Composable
fun SectionWithDate(
    title: String,
    expanded: Boolean,
    onToggle: () -> Unit,
    content: @Composable () -> Unit
) {
    var selectedRange by remember { mutableStateOf("No date selected") }

    SectionCard(
        title = title,
        expanded = expanded,
        onToggle = onToggle
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // 🔑 breathing room inside section
            verticalArrangement = Arrangement.spacedBy(12.dp) // 🔑 consistent spacing
        ) {
            // Date Range Picker
            DateRangePickerSection(
                title = "Select $title Date Range"
            ) { start, end ->
                selectedRange = "$start → $end"
            }

            // Chosen Range Display
            AssistChip(
                onClick = { /* optional: re-open picker */ },
                label = { Text(selectedRange) },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )

            Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)

            // Section Content
            content()
        }
    }
}