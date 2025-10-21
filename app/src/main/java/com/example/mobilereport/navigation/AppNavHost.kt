package com.example.mobilereport.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mobilereport.ui.login.LoginScreen
import com.example.mobilereport.ui.reports.*
import com.example.mobilereport.ui.reports.details.*

@Composable
fun AppNavHost(
    navController: NavHostController,
    isDarkMode: Boolean,
    onToggleTheme: () -> Unit
) {
    var loginError by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    NavHost(navController = navController, startDestination = "login") {
        // 🔑 Login
        composable("login") {
            LoginScreen(
                onLogin = { user, pass ->
                    isLoading = true
                    if (user == "admin" && pass == "1234") {
                        loginError = null
                        navController.navigate("reports") {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    } else {
                        loginError = "Invalid username or password"
                    }
                    isLoading = false
                },
                isLoading = isLoading,
                errorMessage = loginError
            )
        }

        // 🔑 Dashboard
        composable("reports") {
            ReportsScreen(
                isDarkMode = isDarkMode,
                onToggleTheme = onToggleTheme,
                navController = navController
            )
        }

        // 🔑 Main Screens
        composable("remittanceMain") { RemittanceMainScreen(navController) }
        composable("dispatcherMain") { DispatcherMainScreen(navController) }
        composable("inspectorMain") { InspectorMainScreen(navController) }
        composable("expensesMain") { ExpensesMainScreen(navController) }

        // 🔑 Detail Screens (with date argument)
        composable(
            route = "remittanceDetail/{date}",
            arguments = listOf(navArgument("date") { defaultValue = "" })
        ) { backStackEntry ->
            val date = backStackEntry.arguments?.getString("date") ?: ""
            RemittanceDetailScreen(date, navController)
        }

        composable(
            route = "dispatcherDetail/{date}",
            arguments = listOf(navArgument("date") { defaultValue = "" })
        ) { backStackEntry ->
            val date = backStackEntry.arguments?.getString("date") ?: ""
            DispatcherDetailScreen(date, navController)
        }

        composable(
            route = "inspectorDetail/{date}",
            arguments = listOf(navArgument("date") { defaultValue = "" })
        ) { backStackEntry ->
            val date = backStackEntry.arguments?.getString("date") ?: ""
            InspectorDetailScreen(date, navController)
        }

        composable(
            route = "expensesDetail/{date}",
            arguments = listOf(navArgument("date") { defaultValue = "" })
        ) { backStackEntry ->
            val date = backStackEntry.arguments?.getString("date") ?: ""
            ExpensesDetailScreen(date, navController)
        }
    }
}