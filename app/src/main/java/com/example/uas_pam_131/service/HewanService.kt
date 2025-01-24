package com.example.uas_pam_131.service

import com.example.uas_pam_131.model.Hewan
import com.example.uas_pam_131.model.HewanResponse
import com.example.uas_pam_131.model.HewanResponseDetail
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

interface HewanService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("hewan/")
    suspend fun getHewan(): HewanResponse

    @GET("hewan/{id_hewan}")
    suspend fun getHewanById(@Path("id_Hewan") id_hewan: Int): HewanResponseDetail

    @POST("hewan/add")
    suspend fun insertHewan(@Body hewan: Hewan)

    @PUT("hewan/{id_hewan}")
    suspend fun updateHewan(@Path("id_hewan")id_hewan: Int, @Body hewan: Hewan)

    @DELETE("hewan/{id_hewan}")
    suspend fun deleteHewan(@Path("id_hewan")id_hewan: Int): Response<Void>
}