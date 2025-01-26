package com.example.uas_pam_131.service

import com.example.uas_pam_131.model.Kandang
import com.example.uas_pam_131.model.KandangResponse
import com.example.uas_pam_131.model.KandangResponseDetail
import com.example.uas_pam_131.model.Monitoring
import com.example.uas_pam_131.model.MonitoringResponse
import com.example.uas_pam_131.model.MonitoringResponseDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MonitoringService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("monitoring/")
    suspend fun getMonitoring(): MonitoringResponse

    @GET("monitoring/{id_monitoring}")
    suspend fun getMonitoringById(@Path("id_monitoring") id_monitoring: Int): MonitoringResponseDetail

    @POST("monitoring/add")
    suspend fun insertMonitoring(@Body monitoring: Monitoring)

    @PUT("monitoring/{id_monitoring}")
    suspend fun updateMonitoring(@Path("id_monitoring")id_monitoring: Int, @Body monitoring: Monitoring)

    @DELETE("monitoring/{id_monitoring}")
    suspend fun deleteMonitoring(@Path("id_monitoring")id_monitoring: Int): Response<Void>
}