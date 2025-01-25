package com.example.uas_pam_131.service

import com.example.uas_pam_131.model.Kandang
import com.example.uas_pam_131.model.KandangResponse
import com.example.uas_pam_131.model.KandangResponseDetail
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

interface KandangService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("kandang/")
    suspend fun getKandang(): KandangResponse

    @GET("kandang/{id_kandang}")
    suspend fun getKandangById(@Path("id_kandang") id_kandang: Int): KandangResponseDetail

    @POST("kandang/add")
    suspend fun insertKandang(@Body kandang: Kandang)

    @PUT("kandang/{id_kandang}")
    suspend fun updateKandang(@Path("id_kandang")id_kandang: Int, @Body kandang: Kandang)

    @DELETE("kandang/{id_kandang}")
    suspend fun deleteKandang(@Path("id_kandang")id_kandang: Int): Response<Void>
}