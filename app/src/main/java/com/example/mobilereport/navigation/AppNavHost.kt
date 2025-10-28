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

        // 🔑 Range-level Detail Screens
        composable(
            route = "remittanceDetail/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("startDate") { defaultValue = "" },
                navArgument("endDate") { defaultValue = "" }
            )
        ) { backStackEntry ->
            val start = backStackEntry.arguments?.getString("startDate") ?: ""
            val end = backStackEntry.arguments?.getString("endDate") ?: ""
            RemittanceDetailScreen(start, end, navController)
        }

        composable(
            route = "dispatcherDetail/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("startDate") { defaultValue = "" },
                navArgument("endDate") { defaultValue = "" }
            )
        ) { backStackEntry ->
            val start = backStackEntry.arguments?.getString("startDate") ?: ""
            val end = backStackEntry.arguments?.getString("endDate") ?: ""
            DispatcherDetailScreen(start, end, navController)
        }

        composable(
            route = "inspectorDetail/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("startDate") { defaultValue = "" },
                navArgument("endDate") { defaultValue = "" }
            )
        ) { backStackEntry ->
            val start = backStackEntry.arguments?.getString("startDate") ?: ""
            val end = backStackEntry.arguments?.getString("endDate") ?: ""
            InspectorDetailScreen(start, end, navController)
        }

        composable(
            route = "expensesDetail/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("startDate") { defaultValue = "" },
                navArgument("endDate") { defaultValue = "" }
            )
        ) { backStackEntry ->
            val start = backStackEntry.arguments?.getString("startDate") ?: ""
            val end = backStackEntry.arguments?.getString("endDate") ?: ""
            ExpensesDetailScreen(start, end, navController)
        }

        // 🔑 Day-level Detail Screens (with inclusive range)
        composable(
            route = "remittanceDetailDay/{date}/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("date") { defaultValue = "" },
                navArgument("startDate") { defaultValue = "" },
                navArgument("endDate") { defaultValue = "" }
            )
        ) { backStackEntry ->
            val date = backStackEntry.arguments?.getString("date") ?: ""
            val start = backStackEntry.arguments?.getString("startDate") ?: ""
            val end = backStackEntry.arguments?.getString("endDate") ?: ""
            RemittanceDetailDayScreen(date, start, end, navController)
        }

        composable(
            route = "dispatcherDetailDay/{date}/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("date") { defaultValue = "" },
                navArgument("startDate") { defaultValue = "" },
                navArgument("endDate") { defaultValue = "" }
            )
        ) { backStackEntry ->
            val date = backStackEntry.arguments?.getString("date") ?: ""
            val start = backStackEntry.arguments?.getString("startDate") ?: ""
            val end = backStackEntry.arguments?.getString("endDate") ?: ""
            DispatcherDetailDayScreen(date, start, end, navController)
        }

        // 🔑 Inspector Names per Date (middle layer)
        composable(
            route = "inspectorNames/{date}/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("date") { defaultValue = "" },
                navArgument("startDate") { defaultValue = "" },
                navArgument("endDate") { defaultValue = "" }
            )
        ) { backStackEntry ->
            val date = backStackEntry.arguments?.getString("date") ?: ""
            val start = backStackEntry.arguments?.getString("startDate") ?: ""
            val end = backStackEntry.arguments?.getString("endDate") ?: ""
            InspectorNamesScreen(date, start, end, navController)
        }

        // ✅ Inspector Day route with inspectorName
        composable(
            route = "inspectorDetailDay/{inspectorName}/{date}/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("inspectorName") { defaultValue = "" },
                navArgument("date") { defaultValue = "" },
                navArgument("startDate") { defaultValue = "" },
                navArgument("endDate") { defaultValue = "" }
            )
        ) { backStackEntry ->
            val inspectorName = backStackEntry.arguments?.getString("inspectorName") ?: ""
            val date = backStackEntry.arguments?.getString("date") ?: ""
            val start = backStackEntry.arguments?.getString("startDate") ?: ""
            val end = backStackEntry.arguments?.getString("endDate") ?: ""
            InspectorDetailDayScreen(inspectorName, date, start, end, navController)
        }

        composable(
            route = "expensesDetailDay/{date}/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("date") { defaultValue = "" },
                navArgument("startDate") { defaultValue = "" },
                navArgument("endDate") { defaultValue = "" }
            )
        ) { backStackEntry ->
            val date = backStackEntry.arguments?.getString("date") ?: ""
            val start = backStackEntry.arguments?.getString("startDate") ?: ""
            val end = backStackEntry.arguments?.getString("endDate") ?: ""
            ExpensesDetailDayScreen(date, start, end, navController)
        }
    }
}