package com.example.mobilereport.ui.reports

import com.example.mobilereport.ui.reports.ReportViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilereport.R
import com.example.mobilereport.model.Report

class ReportsAdapter : RecyclerView.Adapter<ReportViewHolder>() {
    private val data = mutableListOf<Report>()

    fun submitList(items: List<Report>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_report, parent, false)
        return ReportViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}