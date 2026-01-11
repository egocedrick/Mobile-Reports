package com.example.mobilereport.network

import com.example.mobilereport.model.Report
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReportService {
    @GET("api/reports/company/{companyId}")
    fun getReportsByCompany(
        @Path("companyId") companyId: Int,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): Call<List<Report>>
}