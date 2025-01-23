package com.example.uas_pam_131.service

import com.example.uas_pam_131.model.Petugas
import com.example.uas_pam_131.model.PetugasResponse
import com.example.uas_pam_131.model.PetugasResponseDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PetugasService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("petugas/")
    suspend fun getPetugas(): PetugasResponse

    @GET("petugas/{id_petugas}")
    suspend fun getPetugasById(@Path("id_petugas") id_petugas: Int): PetugasResponseDetail

    @POST("petugas/add")
    suspend fun insertPetugas(@Body petugas: Petugas)

    @PUT("petugas/{id_petugas}")
    suspend fun updatePetugas(@Path("id_petugas")id_petugas: Int, @Body petugas: Petugas)

    @DELETE("petugas{id_petugas}")
    suspend fun deletePetugas(@Path("id_petugas")id_petugas: Int): Response<Void>
}