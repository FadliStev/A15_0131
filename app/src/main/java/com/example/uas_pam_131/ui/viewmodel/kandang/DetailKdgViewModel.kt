package com.example.uas_pam_131.ui.viewmodel.kandang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam_131.model.Hewan
import com.example.uas_pam_131.model.Kandang
import com.example.uas_pam_131.repository.HewanRepository
import com.example.uas_pam_131.repository.KandangRepository
import com.example.uas_pam_131.ui.viewmodel.hewan.InsertHwnUiEvent
import kotlinx.coroutines.launch

class DetailKdgViewModel (
    private val kdg: KandangRepository
): ViewModel(){
    var kdgUiState by mutableStateOf(DetailKdgUiState())
        private set

    fun fetchDetailKadang(id_kandang : Int) {
        viewModelScope.launch {
            kdgUiState = DetailKdgUiState(isLoading = true)
            try {
                val kandang = kdg.getKandangById(id_kandang).data

                kdgUiState = DetailKdgUiState(detailKdgUiEvent = kandang.toDetailKdgEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                kdgUiState = DetailKdgUiState(isError = true, errorMessage = "Failed to fetch details: ${e.message}")
            }
        }
    }
}

data class DetailKdgUiState(
    val detailKdgUiEvent: InsertKdgUiEvent = InsertKdgUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
){
    val isUiEventNotEmpty: Boolean
        get() = detailKdgUiEvent != InsertKdgUiEvent()
}

fun Kandang.toDetailKdgEvent(): InsertKdgUiEvent {
    return InsertKdgUiEvent(
        id_kandang = id_kandang,
        nama_hewan = nama_hewan,
        kapasitas = kapasitas,
        lokasi = lokasi,
    )
}