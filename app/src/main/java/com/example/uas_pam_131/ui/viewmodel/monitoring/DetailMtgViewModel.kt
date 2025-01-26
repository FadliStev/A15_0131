package com.example.uas_pam_131.ui.viewmodel.monitoring

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam_131.model.Monitoring
import com.example.uas_pam_131.repository.MonitroingRepository
import kotlinx.coroutines.launch

class DetailMtgViewModel (
    private val mtg: MonitroingRepository
): ViewModel(){
    var mtgUiState by mutableStateOf(DetailMtgUiState())
        private set

    fun fetchDetailMonitoring(id_monitoring : Int) {
        viewModelScope.launch {
            mtgUiState = DetailMtgUiState(isLoading = true)
            try {
                val monitoring = mtg.getMonitoringById(id_monitoring).data

                mtgUiState = DetailMtgUiState(detailMtgUiEvent = monitoring.toDetailMtgEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                mtgUiState = DetailMtgUiState(isError = true, errorMessage = "Failed to fetch details: ${e.message}")
            }
        }
    }
}

data class DetailMtgUiState(
    val detailMtgUiEvent: InsertMtgUiEvent = InsertMtgUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
){
    val isUiEventNotEmpty: Boolean
        get() = detailMtgUiEvent != InsertMtgUiEvent()
}

fun Monitoring.toDetailMtgEvent(): InsertMtgUiEvent {
    return InsertMtgUiEvent(
        id_monitoring = id_monitoring,
        id_kandang = id_kandang,
        nama_petugas = nama_petugas,
        nama_hewan = nama_hewan,
        tanggal_monitoring = tanggal_monitoring,
        hewan_sakit = hewan_sakit,
        hewan_sehat = hewan_sehat,
        status = status
    )
}