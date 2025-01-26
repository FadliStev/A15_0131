package com.example.uas_pam_131.ui.viewmodel.monitoring

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam_131.repository.KandangRepository
import com.example.uas_pam_131.repository.MonitroingRepository
import com.example.uas_pam_131.ui.navigation.DestinasiUpdateKdg
import com.example.uas_pam_131.ui.navigation.DestinasiUpdateMtg
import com.example.uas_pam_131.ui.viewmodel.kandang.InsertKdgUiEvent
import com.example.uas_pam_131.ui.viewmodel.kandang.InsertKdgUiState
import com.example.uas_pam_131.ui.viewmodel.kandang.toKdg
import com.example.uas_pam_131.ui.viewmodel.kandang.toUiStateKdg
import kotlinx.coroutines.launch

class UpdateMtgViewModel(
    savedStateHandle: SavedStateHandle,
    private val mtg: MonitroingRepository
): ViewModel() {
    var mtgupdateUiState by mutableStateOf(InsertMtgUiState())
        private set

    private val _id_monitoring: String = checkNotNull(savedStateHandle[DestinasiUpdateMtg.ID_MONITORING])

    init {
        viewModelScope.launch {
            mtgupdateUiState = mtg.getMonitoringById(_id_monitoring.toInt()).data
                .toUiStateMtg()
        }
    }

    fun updateInsertMtgState(insertMtgUiEvent: InsertMtgUiEvent) {
        mtgupdateUiState = InsertMtgUiState(insertMtgUiEvent = insertMtgUiEvent)
    }

    suspend fun updateMtg() {
        viewModelScope.launch {
            try {
                mtg.updateMonitoring(_id_monitoring.toInt(), mtgupdateUiState.insertMtgUiEvent.toMtg())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}