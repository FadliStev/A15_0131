package com.example.uas_pam_131.ui.viewmodel.petugas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam_131.model.Petugas
import com.example.uas_pam_131.repository.PetugasRepository
import kotlinx.coroutines.launch

class DetailViewModel (
    private val ptgsRepository: PetugasRepository
): ViewModel(){
    var ptgsUiState by mutableStateOf(DetailPtgsUiState())
        private set

    fun fetchDetailPetugas(id_petugas : Int) {
        viewModelScope.launch {
            ptgsUiState = DetailPtgsUiState(isLoading = true)
            try {
                val petugas = ptgsRepository.getPetugasById(id_petugas).data

                ptgsUiState = DetailPtgsUiState(detailPtgsUiEvent = petugas.toDetailPtgsEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                ptgsUiState = DetailPtgsUiState(isError = true, errorMessage = "Failed to fetch details: ${e.message}")
            }
        }
    }
}

data class DetailPtgsUiState(
    val detailPtgsUiEvent: InsertPtgsUiEvent = InsertPtgsUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
){
    val isUiEventNotEmpty: Boolean
        get() = detailPtgsUiEvent != InsertPtgsUiEvent()
}

fun Petugas.toDetailPtgsEvent(): InsertPtgsUiEvent{
    return InsertPtgsUiEvent(
        id_petugas = id_petugas,
        nama_petugas = nama_petugas,
        jabatan = jabatan
    )
}