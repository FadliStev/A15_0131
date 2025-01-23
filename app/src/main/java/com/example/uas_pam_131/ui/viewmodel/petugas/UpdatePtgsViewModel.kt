package com.example.uas_pam_131.ui.viewmodel.petugas

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam_131.repository.PetugasRepository
import com.example.uas_pam_131.ui.navigation.DestinasiUpdatePtgs
import kotlinx.coroutines.launch

class UpdatePtgsViewModel(
    savedStateHandle: SavedStateHandle,
    private val ptgs: PetugasRepository
): ViewModel() {
    var ptgsupdateUiState by mutableStateOf(InsertPtgsUiState())
        private set

    private val _id_petugas: String = checkNotNull(savedStateHandle[DestinasiUpdatePtgs.ID_PETUGAS])

    init {
        viewModelScope.launch {
            ptgsupdateUiState = ptgs.getPetugasById(_id_petugas.toInt()).data
                .toUiStatePtgs()
        }
    }

    fun updateInsertPtgsState(insertPtgsUiEvent: InsertPtgsUiEvent) {
        ptgsupdateUiState = InsertPtgsUiState(insertPtgsUiEvent = insertPtgsUiEvent)
    }

    suspend fun updatePtgs() {
        viewModelScope.launch {
            try {
                ptgs.updatePetugas(_id_petugas.toInt(), ptgsupdateUiState.insertPtgsUiEvent.toPtgs())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}