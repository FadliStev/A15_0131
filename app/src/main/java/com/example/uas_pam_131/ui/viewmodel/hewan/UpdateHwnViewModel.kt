package com.example.uas_pam_131.ui.viewmodel.hewan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam_131.repository.HewanRepository
import com.example.uas_pam_131.repository.PetugasRepository
import com.example.uas_pam_131.ui.navigation.DestinasiUpdateHwn
import com.example.uas_pam_131.ui.navigation.DestinasiUpdatePtgs
import com.example.uas_pam_131.ui.viewmodel.petugas.InsertPtgsUiEvent
import com.example.uas_pam_131.ui.viewmodel.petugas.InsertPtgsUiState
import com.example.uas_pam_131.ui.viewmodel.petugas.toPtgs
import com.example.uas_pam_131.ui.viewmodel.petugas.toUiStatePtgs
import kotlinx.coroutines.launch

class UpdateHwnViewModel(
    savedStateHandle: SavedStateHandle,
    private val hwn: HewanRepository
): ViewModel() {
    var hwnupdateUiState by mutableStateOf(InsertHwnUiState())
        private set

    private val _id_hewan: String = checkNotNull(savedStateHandle[DestinasiUpdateHwn.ID_HEWAN])

    init {
        viewModelScope.launch {
            hwnupdateUiState = hwn.getHewanById(_id_hewan.toInt()).data
                .toUiStateHwn()
        }
    }

    fun updateInsertHwnState(insertHwnUiEvent: InsertHwnUiEvent) {
        hwnupdateUiState = InsertHwnUiState(insertHwnUiEvent = insertHwnUiEvent)
    }

    suspend fun updateHwn() {
        viewModelScope.launch {
            try {
                hwn.updateHewan(_id_hewan.toInt(), hwnupdateUiState.insertHwnUiEvent.toHwn())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}