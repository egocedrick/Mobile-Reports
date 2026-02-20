package com.example.mobilereport.data

import com.example.mobilereport.model.*
/*
object MockData {
    val remittances = listOf(
        RemittanceItem(
            dateRequested = "2025-10-07",
            company = "mPAD",
            partialCommission = 120.0,
            vehicle = "BUS-101",
            grossIncome = 8500.0,
            dateDispatched = "2025-10-07",
            timeDispatched = "06:30 AM",
            dateRemitted = "2025-10-07",
            route = "A — B",
            driver = "Juan Dela Cruz",
            conductor = "Pedro Santos",
            trips = 12,
            kmRun = 145.5,
            litersOut = 60.0,
            loanValeDamages = 250.0,
            driverShare = 1000.0,
            conductorShare = 500.0,
            others = 150.0,
            netCash = 7600.0,
            amountRemittance = 7350.0,
            overShort = -250.0,
            paxAdult = 320,
            paxSenior = 12,
            paxStudent = 45,
            baggageCount = 28
        ),
        RemittanceItem(
            dateRequested = "2025-10-07",
            company = "ErJohn",
            partialCommission = 80.0,
            vehicle = "BUS-202",
            grossIncome = 5400.0,
            dateDispatched = "2025-10-07",
            timeDispatched = "07:15 AM",
            dateRemitted = "2025-10-07",
            route = "C — D",
            driver = "Maria Lopez",
            conductor = "Ramon Cruz",
            trips = 9,
            kmRun = 98.0,
            litersOut = 42.5,
            loanValeDamages = 0.0,
            driverShare = 700.0,
            conductorShare = 350.0,
            others = 50.0,
            netCash = 5300.0,
            amountRemittance = 5300.0,
            overShort = 0.0,
            paxAdult = 210,
            paxSenior = 8,
            paxStudent = 30,
            baggageCount = 12
        )
    )

    val dispatches = listOf(
        DispatcherItem(
            requestedDate = "2025-10-07",
            type = "D",
            date = "2025-10-07",
            busNumber = "BUS-101",
            dispatcher = "D. Reyes",
            driver = "Juan Dela Cruz",
            conductor = "Pedro Santos",
            dispatchTo = "Terminal A",
            dispatchFrom = "Garage 1",
            time = "06:30 AM"
        ),
        DispatcherItem(
            requestedDate = "2025-10-07",
            type = "R",
            date = "2025-10-07",
            busNumber = "BUS-202",
            dispatcher = "L. Gomez",
            driver = "Maria Lopez",
            conductor = "Ramon Cruz",
            dispatchTo = "Terminal B",
            dispatchFrom = "Garage 2",
            time = "07:15 AM"
        )
    )


    val inspectors = listOf(
        InspectorItem(
            requestedRange = "2025-10-01 — 2025-10-07",
            date = "2025-10-01",
            busNumber = "332",
            numPassengers = 120,
            kmPost = 45.2,
            inspector = "Juan Dela Cruz",
            driver = "D. Pororog",
            conductor = "A. Castro",
            time = "10:00 AM",
            discrepancy = 0,
            remainingPassenger = 15
        ),
        InspectorItem(
            requestedRange = "2025-10-01 — 2025-10-07",
            date = "2025-10-01",
            busNumber = "234",
            numPassengers = 98,
            kmPost = 37.8,
            inspector = "Juan Dela Cruz",
            driver = "R. Bana",
            conductor = "D. Castro",
            time = "10:11 AM",
            discrepancy = 0,
            remainingPassenger = 12
        ),
        InspectorItem(
            requestedRange = "2025-10-01 — 2025-10-07",
            date = "2025-10-01",
            busNumber = "324",
            numPassengers = 140,
            kmPost = 52.1,
            inspector = "Juan Dela Cruz",
            driver = "B. Santos",
            conductor = "A. Santos",
            time = "12:50 PM",
            discrepancy = 2,
            remainingPassenger = 20
        ),
        InspectorItem(
            requestedRange = "2025-10-01 — 2025-10-07",
            date = "2025-10-01",
            busNumber = "111",
            numPassengers = 110,
            kmPost = 41.3,
            inspector = "Ricardo D.",
            driver = "D. Castro",
            conductor = "D. Castro",
            time = "11:15 AM",
            discrepancy = 0,
            remainingPassenger = 8
        ),
        InspectorItem(
            requestedRange = "2025-10-01 — 2025-10-07",
            date = "2025-10-07",
            busNumber = "BUS-101",
            numPassengers = 320,
            kmPost = 145.5,
            inspector = "Inspector A",
            driver = "Juan Dela Cruz",
            conductor = "Pedro Santos",
            time = "09:40 AM",
            discrepancy = 1,
            remainingPassenger = 25
        ),
        InspectorItem(
            requestedRange = "2025-10-01 — 2025-10-07",
            date = "2025-10-07",
            busNumber = "BUS-202",
            numPassengers = 210,
            kmPost = 98.0,
            inspector = "Inspector B",
            driver = "Maria Lopez",
            conductor = "Ramon Cruz",
            time = "10:20 AM",
            discrepancy = 0,
            remainingPassenger = 18
        )
    )

    // Alias so existing screens using MockData.inspections compile without changing imports
    val inspections: List<InspectorItem> = inspectors

    // Expanded Expenses data for tabular reports
    val expenses = listOf(
        *Array(10) { ExpensesItem("2025-10-01", "Toll Fee", 160.0) },
        *Array(9) { ExpensesItem("2025-10-01", "Parking", 100.0) },
        *Array(9) { ExpensesItem("2025-10-01", "Washing", 150.0) },
        ExpensesItem("2025-10-01", "Fuel-in", 250.0),
        *Array(8) { ExpensesItem("2025-10-01", "Fuel-in", 200.0) },
        ExpensesItem("2025-10-01", "Fuel-in", 210.0),
        ExpensesItem("2025-10-01", "BUS", 101.0),
        ExpensesItem("2025-10-01", "BUS", 321.0),
        ExpensesItem("2025-10-01", "BUS", 455.0),
        ExpensesItem("2025-10-01", "BUS", 320.0),
        ExpensesItem("2025-10-01", "BUS", 345.0),
        ExpensesItem("2025-10-01", "BUS", 654.0),
        ExpensesItem("2025-10-01", "BUS", 123.0),

        ExpensesItem("2025-10-07", "Fuel", 3200.0, notes = "Diesel refill BUS-101"),
        ExpensesItem("2025-10-07", "Maintenance", 850.0, notes = "Brake pads BUS-202"),
        ExpensesItem("2025-10-06", "Supplies", 240.0, notes = "Office supplies")
    ).toList()
}

 */