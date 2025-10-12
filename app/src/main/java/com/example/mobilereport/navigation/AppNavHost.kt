package com.example.mobilereport.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mobilereport.ui.login.LoginScreen
import com.example.mobilereport.ui.reports.ReportsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    isDarkMode: Boolean,
    onToggleTheme: () -> Unit
) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLogin = { user, pass ->
                    if (user.isNotEmpty() && pass.isNotEmpty()) {
                        navController.navigate("reports") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                }
            )
        }
        composable("reports") {
            ReportsScreen(
                isDarkMode = isDarkMode,
                onToggleTheme = onToggleTheme
            )
        }
    }
}