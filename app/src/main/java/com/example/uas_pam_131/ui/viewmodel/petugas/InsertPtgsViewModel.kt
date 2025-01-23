package com.example.uas_pam_131.ui.viewmodel.petugas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam_131.model.Petugas
import com.example.uas_pam_131.repository.PetugasRepository
import kotlinx.coroutines.launch


class InsertPtgsViewModel(
    private val ptgs: PetugasRepository

): ViewModel(){

    var ptgsUiState by mutableStateOf(InsertPtgsUiState())
        private set

    fun updateInsertPtgsState(insertPtgsUiEvent: InsertPtgsUiEvent){
        ptgsUiState = InsertPtgsUiState(insertPtgsUiEvent = insertPtgsUiEvent)
    }

    suspend fun insertPtgs(){
        viewModelScope.launch {
            try {
                ptgs.insertPetugas(ptgsUiState.insertPtgsUiEvent.toPtgs())
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertPtgsUiState(
    val insertPtgsUiEvent: InsertPtgsUiEvent = InsertPtgsUiEvent()
)

data class InsertPtgsUiEvent(
    val id_petugas: Int = 0,
    val nama_petugas: String = "",
    val jabatan: String = ""
)

fun InsertPtgsUiEvent.toPtgs(): Petugas = Petugas(
    id_petugas = id_petugas,
    nama_petugas = nama_petugas,
    jabatan = jabatan
)

fun Petugas.toUiStatePtgs(): InsertPtgsUiState = InsertPtgsUiState(
    insertPtgsUiEvent = toInsertPtgsUiEvent()
)

fun Petugas.toInsertPtgsUiEvent(): InsertPtgsUiEvent = InsertPtgsUiEvent(
    id_petugas = id_petugas,
    nama_petugas = nama_petugas,
    jabatan = jabatan

)