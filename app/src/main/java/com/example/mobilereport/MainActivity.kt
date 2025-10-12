package com.example.mobilereport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.example.mobilereport.viewmodel.ThemeViewModel
import com.example.mobilereport.ui.theme.MobileReportTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import com.example.mobilereport.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Create ThemeViewModel using Factory
        val themeViewModel = ViewModelProvider(
            this,
            ThemeViewModel.Factory(applicationContext)
        )[ThemeViewModel::class.java]

        setContent {
            val isDarkMode by themeViewModel.isDarkMode.collectAsState()

            MobileReportTheme(darkTheme = isDarkMode) {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(
                        navController = navController,
                        isDarkMode = isDarkMode,
                        onToggleTheme = { themeViewModel.toggleTheme() }
                    )
                }
            }
        }
    }
}