package com.example.mobilereport.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mobilereport.ui.login.LoginScreen
import com.example.mobilereport.ui.reports.*
import com.example.mobilereport.ui.reports.details.*
import com.example.mobilereport.model.*

@Composable
fun AppNavHost(
    navController: NavHostController,
    isDarkMode: Boolean,
    onToggleTheme: () -> Unit
) {
    var loginError by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    NavHost(navController = navController, startDestination = "login") {

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

        composable("reports") {
            ReportsScreen(
                isDarkMode = isDarkMode,
                onToggleTheme = onToggleTheme,
                navController = navController
            )
        }

        composable("dispatcherMain") { DispatcherMainScreen(navController) }
        composable("inspectorMain") { InspectorMainScreen(navController) }
        composable("expensesMain") { ExpensesMainScreen(navController) }
        composable("remittanceMain") { RemittanceMainScreen(navController) }

        composable(
            "remittanceDetail/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("startDate") { defaultValue = "" },
                navArgument("endDate") { defaultValue = "" }
            )
        ) { backStackEntry ->
            RemittanceDetailScreen(
                startDate = backStackEntry.arguments?.getString("startDate").orEmpty(),
                endDate = backStackEntry.arguments?.getString("endDate").orEmpty(),
                navController = navController,
                remittances = emptyList()
            )
        }

        composable(
            "dispatcherDetail/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("startDate") { defaultValue = "" },
                navArgument("endDate") { defaultValue = "" }
            )
        ) { backStackEntry ->
            DispatcherDetailScreen(
                startDate = backStackEntry.arguments?.getString("startDate").orEmpty(),
                endDate = backStackEntry.arguments?.getString("endDate").orEmpty(),
                navController = navController,
                dispatches = emptyList()
            )
        }

        composable(
            "inspectorDetail/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("startDate") { defaultValue = "" },
                navArgument("endDate") { defaultValue = "" }
            )
        ) { backStackEntry ->
            InspectorDetailScreen(
                startDate = backStackEntry.arguments?.getString("startDate").orEmpty(),
                endDate = backStackEntry.arguments?.getString("endDate").orEmpty(),
                navController = navController,
                inspectors = emptyList()
            )
        }

        composable(
            "expensesDetail/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("startDate") { defaultValue = "" },
                navArgument("endDate") { defaultValue = "" }
            )
        ) { backStackEntry ->
            ExpensesDetailScreen(
                startDate = backStackEntry.arguments?.getString("startDate").orEmpty(),
                endDate = backStackEntry.arguments?.getString("endDate").orEmpty(),
                navController = navController,
                expenses = emptyList()
            )
        }

        composable(
            "remittanceDetailDay/{date}/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("date") { defaultValue = "" },
                navArgument("startDate") { defaultValue = "" },
                navArgument("endDate") { defaultValue = "" }
            )
        ) { backStackEntry ->
            RemittanceDetailDayScreen(
                date = backStackEntry.arguments?.getString("date").orEmpty(),
                startDate = backStackEntry.arguments?.getString("startDate").orEmpty(),
                endDate = backStackEntry.arguments?.getString("endDate").orEmpty(),
                navController = navController,
                remittances = emptyList()
            )
        }

        composable(
            "dispatcherDetailDay/{date}/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("date") { defaultValue = "" },
                navArgument("startDate") { defaultValue = "" },
                navArgument("endDate") { defaultValue = "" }
            )
        ) { backStackEntry ->
            DispatcherDetailDayScreen(
                date = backStackEntry.arguments?.getString("date").orEmpty(),
                startDate = backStackEntry.arguments?.getString("startDate").orEmpty(),
                endDate = backStackEntry.arguments?.getString("endDate").orEmpty(),
                navController = navController,
                itemsForDay = emptyList()
            )
        }

        composable(
            "inspectorNames/{date}/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("date") { defaultValue = "" },
                navArgument("startDate") { defaultValue = "" },
                navArgument("endDate") { defaultValue = "" }
            )
        ) { backStackEntry ->
            InspectorNamesScreen(
                date = backStackEntry.arguments?.getString("date").orEmpty(),
                startDate = backStackEntry.arguments?.getString("startDate").orEmpty(),
                endDate = backStackEntry.arguments?.getString("endDate").orEmpty(),
                navController = navController,
                inspectors = emptyList()
            )
        }

        composable(
            "inspectorDetailDay/{inspectorName}/{date}/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("inspectorName") { defaultValue = "" },
                navArgument("date") { defaultValue = "" },
                navArgument("startDate") { defaultValue = "" },
                navArgument("endDate") { defaultValue = "" }
            )
        ) { backStackEntry ->
            InspectorDetailDayScreen(
                inspectorName = backStackEntry.arguments?.getString("inspectorName").orEmpty(),
                date = backStackEntry.arguments?.getString("date").orEmpty(),
                startDate = backStackEntry.arguments?.getString("startDate").orEmpty(),
                endDate = backStackEntry.arguments?.getString("endDate").orEmpty(),
                navController = navController,
                inspections = emptyList()
            )
        }

        composable(
            "expensesDetailDay/{date}/{startDate}/{endDate}",
            arguments = listOf(
                navArgument("date") { defaultValue = "" },
                navArgument("startDate") { defaultValue = "" },
                navArgument("endDate") { defaultValue = "" }
            )
        ) { backStackEntry ->
            ExpensesDetailDayScreen(
                date = backStackEntry.arguments?.getString("date").orEmpty(),
                startDate = backStackEntry.arguments?.getString("startDate").orEmpty(),
                endDate = backStackEntry.arguments?.getString("endDate").orEmpty(),
                navController = navController,
                expensesForDay = emptyList()
            )
        }
    }
}