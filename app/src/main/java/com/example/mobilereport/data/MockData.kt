package com.example.mobilereport.data

import com.example.mobilereport.model.*

object MockData {
    val remittances = listOf(
        RemittanceItem(
            dateRequested = "2025-10-07",
            company = "mPAD",
            partialCommission = 120.0,
            vehicle = "BUS-101",
            grossIncome = 8500.0,
            dateDispatched = "2025-10-07",
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
            dispatchFrom = "Garage 1"
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
            dispatchFrom = "Garage 2"
        )
    )

    val inspectors = listOf(
        InspectorItem(
            requestedRange = "2025-10-01 — 2025-10-07",
            date = "2025-10-07",
            busNumber = "BUS-101",
            numPassengers = 320,
            kmPost = 145.5,
            inspector = "Inspector A",
            driver = "Juan Dela Cruz",
            conductor = "Pedro Santos"
        ),
        InspectorItem(
            requestedRange = "2025-10-01 — 2025-10-07",
            date = "2025-10-07",
            busNumber = "BUS-202",
            numPassengers = 210,
            kmPost = 98.0,
            inspector = "Inspector B",
            driver = "Maria Lopez",
            conductor = "Ramon Cruz"
        )
    )
}