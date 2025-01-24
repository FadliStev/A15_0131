package com.example.uas_pam_131.repository

import com.example.uas_pam_131.model.Hewan
import com.example.uas_pam_131.model.HewanResponse
import com.example.uas_pam_131.model.HewanResponseDetail
import com.example.uas_pam_131.model.Petugas
import com.example.uas_pam_131.model.PetugasResponse
import com.example.uas_pam_131.model.PetugasResponseDetail
import com.example.uas_pam_131.service.HewanService
import com.example.uas_pam_131.service.PetugasService
import java.io.IOException

interface HewanRepository{

    suspend fun getHewan(): HewanResponse

    suspend fun insertHewan(hewan: Hewan)

    suspend fun updateHewan(id_hewan: Int, hewan: Hewan)

    suspend fun deleteHewan(id_hewan: Int)

    suspend fun getHewanById(id_hewan: Int): HewanResponseDetail
}

class NetworkHewanRepository(

    private val hewanService: HewanService
): HewanRepository{

    override suspend fun insertHewan(hewan: Hewan) {
        hewanService.insertHewan(hewan)
    }

    override suspend fun updateHewan(id_hewan: Int, hewan: Hewan) {
        hewanService.updateHewan(id_hewan, hewan)
    }

    override suspend fun deleteHewan(id_hewan: Int) {
        try {
            val response = hewanService.deleteHewan(id_hewan)
            if (!response.isSuccessful){
                throw IOException("Failed to delete hewan. HTTP Status code:" +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception){
            throw e
        }
    }

    override suspend fun getHewan(): HewanResponse {
        return hewanService.getHewan()
    }


    override suspend fun getHewanById(id_hewan: Int): HewanResponseDetail {
        return hewanService.getHewanById(id_hewan)
    }
}