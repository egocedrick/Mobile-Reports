package com.example.mobilereport.ui.reports.comments

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobilereport.ui.reports.util.DateRangePickerSection
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun SectionWithDate(
    title: String,
    expanded: Boolean,
    onToggle: () -> Unit,
    onDateRangeSelected: (LocalDate, LocalDate) -> Unit = { _, _ -> },
    content: @Composable () -> Unit
) {
    var selectedRange by remember { mutableStateOf<Pair<LocalDate, LocalDate>?>(null) }
    val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")

    SectionCard(
        title = title,
        expanded = expanded,
        onToggle = onToggle
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // ✅ Use the shared DateRangePickerSection
            DateRangePickerSection(
                title = "Select $title Date Range"
            ) { start, end ->
                selectedRange = start to end
                onDateRangeSelected(start, end)
            }

            // Show chip only if a range is selected
            selectedRange?.let { (start, end) ->
                AssistChip(
                    onClick = { /* optional: reopen picker */ },
                    label = {
                        Text(
                            "${start.format(formatter)} → ${end.format(formatter)}",
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                )
            }

            Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)

            // Section Content
            content()
        }
    }
}