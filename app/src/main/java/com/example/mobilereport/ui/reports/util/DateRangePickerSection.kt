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
import java.text.SimpleDateFormat
import java.util.*

// Helper to unwrap context safely
private tailrec fun Context.findActivity(): FragmentActivity? = when (this) {
    is FragmentActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@Composable
fun DateRangePickerSection(
    title: String,
    onDateRangeSelected: (String, String) -> Unit
) {
    var startDate by remember { mutableStateOf("Start Date") }
    var endDate by remember { mutableStateOf("End Date") }
    val context = LocalContext.current

    // 🔑 Extracted picker logic para reusable sa Start at End
    fun openDateRangePicker() {
        val activity = context.findActivity()
        if (activity != null) {
            val picker = MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select dates")
                .build()

            picker.addOnPositiveButtonClickListener { range ->
                val start = range.first
                val end = range.second
                if (start != null && end != null) {
                    val fmt = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    startDate = fmt.format(Date(start))
                    endDate = fmt.format(Date(end))
                    onDateRangeSelected(startDate, endDate)
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
            .padding(16.dp), // 🔑 more breathing room
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(title, style = MaterialTheme.typography.titleMedium)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = startDate,
                onValueChange = {},
                label = { Text("Start Date") },
                modifier = Modifier.weight(1f),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { openDateRangePicker() }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Pick date")
                    }
                }
            )
            OutlinedTextField(
                value = endDate,
                onValueChange = {},
                label = { Text("End Date") },
                modifier = Modifier.weight(1f),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { openDateRangePicker() }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Pick date")
                    }
                }
            )
        }
    }
}