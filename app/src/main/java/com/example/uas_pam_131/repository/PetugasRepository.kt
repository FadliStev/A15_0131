package com.example.uas_pam_131.repository

import com.example.uas_pam_131.model.Petugas
import com.example.uas_pam_131.model.PetugasResponse
import com.example.uas_pam_131.model.PetugasResponseDetail
import com.example.uas_pam_131.service.PetugasService
import java.io.IOException

interface PetugasRepository{

    suspend fun getPetugas(): PetugasResponse

    suspend fun insertPetugas(petugas: Petugas)

    suspend fun updatePetugas(id_petugas: Int, petugas: Petugas)

    suspend fun deletePetugas(id_petugas: Int)

    suspend fun getPetugasById(id_petugas: Int): PetugasResponseDetail
}

class NetworkPetugasRepository(

    private val petugasService: PetugasService
): PetugasRepository{

    override suspend fun insertPetugas(petugas: Petugas) {
        petugasService.insertPetugas(petugas)
    }

    override suspend fun updatePetugas(id_petugas: Int, petugas: Petugas) {
        petugasService.updatePetugas(id_petugas, petugas)
    }

    override suspend fun deletePetugas(id_petugas: Int) {
        try {
            val response = petugasService.deletePetugas(id_petugas)
            if (!response.isSuccessful){
                throw IOException("Failed to delete petugas. HTTP Status code:" +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception){
            throw e
        }
    }

    override suspend fun getPetugas(): PetugasResponse {
        return petugasService.getPetugas()
    }


    override suspend fun getPetugasById(id_petugas: Int): PetugasResponseDetail {
        return petugasService.getPetugasById(id_petugas)
    }
}