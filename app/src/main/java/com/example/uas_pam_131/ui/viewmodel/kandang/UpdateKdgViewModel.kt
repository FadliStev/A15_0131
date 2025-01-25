package com.example.uas_pam_131.ui.viewmodel.kandang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam_131.repository.HewanRepository
import com.example.uas_pam_131.repository.KandangRepository
import com.example.uas_pam_131.ui.navigation.DestinasiUpdateHwn
import com.example.uas_pam_131.ui.navigation.DestinasiUpdateKdg
import kotlinx.coroutines.launch

class UpdateKdgViewModel(
    savedStateHandle: SavedStateHandle,
    private val kdg: KandangRepository
): ViewModel() {
    var kdgupdateUiState by mutableStateOf(InsertKdgUiState())
        private set

    private val _id_kandang: String = checkNotNull(savedStateHandle[DestinasiUpdateKdg.ID_KANDANG])

    init {
        viewModelScope.launch {
            kdgupdateUiState = kdg.getKandangById(_id_kandang.toInt()).data
                .toUiStateKdg()
        }
    }

    fun updateInsertKdgState(insertKdgUiEvent: InsertKdgUiEvent) {
        kdgupdateUiState = InsertKdgUiState(insertKdgUiEvent = insertKdgUiEvent)
    }

    suspend fun updateKdg() {
        viewModelScope.launch {
            try {
                kdg.updateKandang(_id_kandang.toInt(), kdgupdateUiState.insertKdgUiEvent.toKdg())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}