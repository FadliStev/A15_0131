package com.example.uas_pam_131.repository

import com.example.uas_pam_131.model.Kandang
import com.example.uas_pam_131.model.KandangResponse
import com.example.uas_pam_131.model.KandangResponseDetail
import com.example.uas_pam_131.model.Monitoring
import com.example.uas_pam_131.model.MonitoringResponse
import com.example.uas_pam_131.model.MonitoringResponseDetail
import com.example.uas_pam_131.service.KandangService
import com.example.uas_pam_131.service.MonitoringService
import java.io.IOException

interface MonitroingRepository{

    suspend fun getMonitoring(): MonitoringResponse

    suspend fun insertMonitoring(monitoring: Monitoring)

    suspend fun updateMonitoring(id_monitoring: Int, monitoring: Monitoring)

    suspend fun deleteMonitoring(id_monitoring: Int)

    suspend fun getMonitoringById(id_monitoring: Int): MonitoringResponseDetail
}

class NetworkMonitoringRepository(

    private val monitoringService: MonitoringService
): MonitroingRepository{

    override suspend fun insertMonitoring(monitoring: Monitoring) {
        monitoringService.insertMonitoring(monitoring)
    }

    override suspend fun updateMonitoring(id_monitoring: Int, monitoring: Monitoring) {
        monitoringService.updateMonitoring(id_monitoring, monitoring)
    }

    override suspend fun deleteMonitoring(id_monitoring: Int) {
        try {
            val response = monitoringService.deleteMonitoring(id_monitoring)
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

    override suspend fun getMonitoring(): MonitoringResponse {
        return monitoringService.getMonitoring()
    }


    override suspend fun getMonitoringById(id_monitoring: Int): MonitoringResponseDetail {
        return monitoringService.getMonitoringById(id_monitoring)
    }
}