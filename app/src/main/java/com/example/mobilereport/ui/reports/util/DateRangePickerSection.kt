package com.example.mobilereport.ui.reports.util

import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

// Helper to unwrap context safely
private tailrec fun Context.findActivity(): FragmentActivity? = when (this) {
    is FragmentActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@Composable
fun DateRangePickerSection(
    title: String,
    onDateRangeSelected: (LocalDate, LocalDate) -> Unit
) {
    var startDate by remember { mutableStateOf<LocalDate?>(null) }
    var endDate by remember { mutableStateOf<LocalDate?>(null) }
    val context = LocalContext.current

    fun openDateRangePicker() {
        val activity = context.findActivity()
        if (activity != null) {
            val picker = MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select date range")
                .build()

            picker.addOnPositiveButtonClickListener { range ->
                val start = range.first
                val end = range.second
                if (start != null && end != null) {
                    val startLocal = Instant.ofEpochMilli(start).atZone(ZoneId.systemDefault()).toLocalDate()
                    val endLocal = Instant.ofEpochMilli(end).atZone(ZoneId.systemDefault()).toLocalDate()
                    startDate = startLocal
                    endDate = endLocal
                    onDateRangeSelected(startLocal, endLocal)
                }
            }

            picker.show(activity.supportFragmentManager, "DateRangePicker")
        } else {
            Toast.makeText(context, "No activity found", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = startDate?.toString() ?: "Start Date",
                onValueChange = {},
                label = { Text("Start Date") },
                modifier = Modifier.weight(1f),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { openDateRangePicker() }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Pick start date")
                    }
                }
            )
            OutlinedTextField(
                value = endDate?.toString() ?: "End Date",
                onValueChange = {},
                label = { Text("End Date") },
                modifier = Modifier.weight(1f),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { openDateRangePicker() }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Pick end date")
                    }
                }
            )
        }
    }
}