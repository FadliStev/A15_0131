package com.example.uas_pam_131.ui.viewmodel.hewan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam_131.model.Hewan
import com.example.uas_pam_131.repository.HewanRepository
import kotlinx.coroutines.launch

class InsertHwnViewModel(
    private val hwn: HewanRepository

): ViewModel(){

    var hwnUiState by mutableStateOf(InsertHwnUiState())
        private set

    fun updateInsertHwnState(insertHwnUiEvent: InsertHwnUiEvent){
        hwnUiState = InsertHwnUiState(insertHwnUiEvent = insertHwnUiEvent)
    }

    suspend fun insertHwn(){
        viewModelScope.launch {
            try {
                hwn.insertHewan(hwnUiState.insertHwnUiEvent.toHwn())
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertHwnUiState(
    val insertHwnUiEvent: InsertHwnUiEvent = InsertHwnUiEvent()
)

data class InsertHwnUiEvent(
    val id_hewan: Int = 0,
    val nama_hewan: String = "",
    val tipe_pakan: String = "",
    val populasi: String = "",
    val zona_wilayah: String = "",
)

fun InsertHwnUiEvent.toHwn(): Hewan = Hewan(
    id_hewan = id_hewan,
    nama_hewan = nama_hewan,
    tipe_pakan = tipe_pakan,
    populasi = populasi,
    zona_wilayah = zona_wilayah
)

fun Hewan.toUiStateHwn(): InsertHwnUiState = InsertHwnUiState(
    insertHwnUiEvent = toInsertHwnUiEvent()
)

fun Hewan.toInsertHwnUiEvent(): InsertHwnUiEvent = InsertHwnUiEvent(
    id_hewan = id_hewan,
    nama_hewan = nama_hewan,
    tipe_pakan = tipe_pakan,
    populasi = populasi,
    zona_wilayah = zona_wilayah

)