package com.example.uas_pam_131.di

import com.example.uas_pam_131.repository.HewanRepository
import com.example.uas_pam_131.repository.KandangRepository
import com.example.uas_pam_131.repository.MonitroingRepository
import com.example.uas_pam_131.repository.NetworkHewanRepository
import com.example.uas_pam_131.repository.NetworkKandangRepository
import com.example.uas_pam_131.repository.NetworkMonitoringRepository
import com.example.uas_pam_131.repository.NetworkPetugasRepository
import com.example.uas_pam_131.repository.PetugasRepository
import com.example.uas_pam_131.service.HewanService
import com.example.uas_pam_131.service.KandangService
import com.example.uas_pam_131.service.MonitoringService
import com.example.uas_pam_131.service.PetugasService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val petugasRepository: PetugasRepository
    val hewanRepository: HewanRepository
    val kandangRepository: KandangRepository
    val monitroingRepository: MonitroingRepository
}

class PetugasContainer: AppContainer{

    private val baseUrl ="http://10.0.2.2:3000/api/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val petugasService: PetugasService by lazy {
        retrofit.create(PetugasService::class.java)
    }

    override val petugasRepository: PetugasRepository by lazy {
        NetworkPetugasRepository(petugasService)
    }

    private val hewanService: HewanService by lazy {
        retrofit.create(HewanService::class.java)
    }

     override val hewanRepository: HewanRepository by lazy {
        NetworkHewanRepository(hewanService)
    }

    private val kandangService: KandangService by lazy {
        retrofit.create(KandangService::class.java)
    }

    override val kandangRepository: KandangRepository by lazy {
        NetworkKandangRepository(kandangService)
    }

    private val monitoringService: MonitoringService by lazy {
        retrofit.create(MonitoringService::class.java)
    }

    override val monitroingRepository: MonitroingRepository by lazy {
        NetworkMonitoringRepository(monitoringService)
    }

}