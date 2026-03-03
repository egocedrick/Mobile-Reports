# Mobile Reports (UI Prototype)

## Overview
A Kotlin-based Android application that demonstrates a reporting dashboard UI for transport operations.  
Currently **UI-only** with no backend or data integration, but designed to showcase navigation flows, theming, and reporting structures.

## Current Features
- **Login Screen**
  - Default credentials: `Admin / 1234`
  - Dark mode / light mode toggle available even at login.

- **Dashboard**
  - Five main modules:
    - **Dispatch**
    - **Inspect**
    - **Remittance**
    - **Expenses**
    - **GPS**

- **Calendar Integration**
  - Each module (Dispatch, Inspect, Remittance, Expenses) prompts for a **start date and end date**.
  - Displays totals for the selected range:
    - Dispatch → number of buses dispatched
    - Inspect → number of inspectors onboard
    - Remittance → total earnings
    - Expenses → total expenses

- **Detailed View**
  - Clicking a specific date shows more detailed breakdowns per section.

- **GPS Module**
  - Redirects to the GPS provider’s URL via browser.

## In Progress
- **Data Integration**
  - Currently UI-only; backend logic and persistence not yet implemented.
- **Dynamic Reports**
  - Planned connection to actual datasets for live reporting.

## Tech Stack
- **Language**: Kotlin
- **Platform**: Android SDK
- **Architecture**: MVVM
- **UI Features**:
  - Dark/Light mode toggle
  - Calendar date range picker
  - Dashboard navigation

## Impact
- Provides mobile UI for company reports.
- Enables clients to access reports anytime, anywhere.
- Allows targeted monitoring of specific devices or employees (e.g., drivers, conductors).
- Ensures employees can only view reports, not edit or configure them.

## Project Structure
- `/ui` – Login, dashboard, and module layouts
- `/logic` – Placeholder for future reporting logic
- `/data` – (planned) integration with storage or APIs

## Setup Instructions
1. Install the application on the device.
2. Login using default credentials: `Admin / 1234`.
3. Explore the dashboard modules:
   - Select date ranges to view totals.
   - Click dates for detailed breakdowns.
   - Use the GPS button to open the provider URL.

## Notes
- This project is a **UI prototype only** — no backend or data persistence yet.
- Built to demonstrate dashboard design, navigation flows, and theming.
- Part of my mobile security and utility portfolio.

