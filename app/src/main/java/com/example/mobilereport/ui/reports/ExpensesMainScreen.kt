package com.example.mobilereport.ui.reports

import android.annotation.SuppressLint
import androidx.fragment.app.FragmentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@SuppressLint("ContextCastToActivity")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesMainScreen(navController: NavController) {
    val activity = LocalContext.current as FragmentActivity
    var dateRange by remember { mutableStateOf<Pair<LocalDate, LocalDate>?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Expenses Reports") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Button(onClick = {
                val picker = MaterialDatePicker.Builder.dateRangePicker()
                    .setTitleText("Select Date Range")
                    .build()

                picker.addOnPositiveButtonClickListener { selection ->
                    val start = Instant.ofEpochMilli(selection.first!!)
                        .atZone(ZoneId.systemDefault()).toLocalDate()
                    val end = Instant.ofEpochMilli(selection.second!!)
                        .atZone(ZoneId.systemDefault()).toLocalDate()
                    dateRange = start to end
                }

                picker.show(activity.supportFragmentManager, "expenses_range_picker")
            }) {
                Text("Pick Date Range")
            }

            dateRange?.let { (start, end) ->
                Text("Showing results from $start to $end")
                Spacer(Modifier.height(12.dp))

                Button(
                    onClick = {
                        navController.navigate("expensesDetail/$start/$end")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("View Expenses Summary")
                }
            }
        }
    }
}