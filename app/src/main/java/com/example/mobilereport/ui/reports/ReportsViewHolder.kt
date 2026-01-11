package com.example.mobilereport.ui.reports

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilereport.R
import com.example.mobilereport.model.Report

class ReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val idText: TextView = itemView.findViewById(R.id.tvId)
    private val amountText: TextView = itemView.findViewById(R.id.tvAmount)
    private val dateText: TextView = itemView.findViewById(R.id.tvDate)

    fun bind(report: Report) {
        idText.text = "ID: ${report.id}"
        amountText.text = "Amount: ₱${report.amount}"
        dateText.text = "Date: ${report.dateCreated}"
    }
}