package com.example.uas_pam_131.ui.viewmodel.hewan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam_131.model.Hewan
import com.example.uas_pam_131.repository.HewanRepository
import kotlinx.coroutines.launch

class DetailHwnViewModel (
    private val hwn: HewanRepository
): ViewModel(){
    var hwnUiState by mutableStateOf(DetailHwnUiState())
        private set

    fun fetchDetailHewan(id_hewan : Int) {
        viewModelScope.launch {
            hwnUiState = DetailHwnUiState(isLoading = true)
            try {
                val hewan = hwn.getHewanById(id_hewan).data

                hwnUiState = DetailHwnUiState(detailHwnUiEvent = hewan.toDetailHwnEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                hwnUiState = DetailHwnUiState(isError = true, errorMessage = "Failed to fetch details: ${e.message}")
            }
        }
    }
}

data class DetailHwnUiState(
    val detailHwnUiEvent: InsertHwnUiEvent = InsertHwnUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
){
    val isUiEventNotEmpty: Boolean
        get() = detailHwnUiEvent != InsertHwnUiEvent()
}

fun Hewan.toDetailHwnEvent(): InsertHwnUiEvent {
    return InsertHwnUiEvent(
        id_hewan = id_hewan,
        nama_hewan = nama_hewan,
        tipe_pakan = tipe_pakan,
        populasi = populasi,
        zona_wilayah = zona_wilayah
    )
}