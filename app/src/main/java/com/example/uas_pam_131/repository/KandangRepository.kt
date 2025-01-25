package com.example.uas_pam_131.repository

import com.example.uas_pam_131.model.Hewan
import com.example.uas_pam_131.model.HewanResponse
import com.example.uas_pam_131.model.HewanResponseDetail
import com.example.uas_pam_131.model.Kandang
import com.example.uas_pam_131.model.KandangResponse
import com.example.uas_pam_131.model.KandangResponseDetail
import com.example.uas_pam_131.service.HewanService
import com.example.uas_pam_131.service.KandangService
import java.io.IOException

interface KandangRepository{

    suspend fun getKandang(): KandangResponse

    suspend fun insertKandang(kandang: Kandang)

    suspend fun updateKandang(id_kandang: Int, kandang: Kandang)

    suspend fun deleteKandang(id_kandang: Int)

    suspend fun getKandangById(id_kandang: Int): KandangResponseDetail
}

class NetworkKandangRepository(

    private val kandangService: KandangService
): KandangRepository{

    override suspend fun insertKandang(kandang: Kandang) {
        kandangService.insertKandang(kandang)
    }

    override suspend fun updateKandang(id_kandang: Int, kandang: Kandang) {
        kandangService.updateKandang(id_kandang, kandang)
    }

    override suspend fun deleteKandang(id_kandang: Int) {
        try {
            val response = kandangService.deleteKandang(id_kandang)
            if (!response.isSuccessful){
                throw IOException("Failed to delete kandang. HTTP Status code:" +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception){
            throw e
        }
    }

    override suspend fun getKandang(): KandangResponse {
        return kandangService.getKandang()
    }


    override suspend fun getKandangById(id_kandang: Int): KandangResponseDetail {
        return kandangService.getKandangById(id_kandang)
    }
}