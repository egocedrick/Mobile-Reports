package com.example.mobilereport.ui.reports

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilereport.R
import com.example.mobilereport.model.Report
import com.example.mobilereport.network.ApiClient
import com.example.mobilereport.network.ReportService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReportsActivity : AppCompatActivity() {

    private lateinit var adapter: ReportsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reports)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerReports)
        adapter = ReportsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val service = ApiClient.retrofit.create(ReportService::class.java)
        service.getReportsByCompany(2, "2025-04-01", "2025-05-28")
            .enqueue(object : Callback<List<Report>> {
                override fun onResponse(call: Call<List<Report>>, response: Response<List<Report>>) {
                    if (response.isSuccessful) {
                        adapter.submitList(response.body() ?: emptyList())
                    } else {
                        Log.e("API_ERROR", "Response code: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<Report>>, t: Throwable) {
                    Log.e("API_ERROR", "Failure: ${t.message}")
                }
            })
    }
}