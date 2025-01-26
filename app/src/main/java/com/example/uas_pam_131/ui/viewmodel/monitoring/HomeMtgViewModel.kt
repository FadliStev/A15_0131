package com.example.uas_pam_131.ui.viewmodel.monitoring

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.uas_pam_131.model.Kandang
import com.example.uas_pam_131.model.Monitoring
import com.example.uas_pam_131.repository.KandangRepository
import com.example.uas_pam_131.repository.MonitroingRepository
import com.example.uas_pam_131.ui.viewmodel.kandang.HomeKdgUiState
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeMtgUiState{
    data class Success(val monitoring: List<Monitoring>): HomeMtgUiState()
    object Error: HomeMtgUiState()
    object Loading: HomeMtgUiState()

}

class HomeMtgViewModel(
    private val mtg: MonitroingRepository
): ViewModel() {
    var mtgUiState: HomeMtgUiState by mutableStateOf(HomeMtgUiState.Loading)
        private set

    init {
        getMtg()
    }

    fun getMtg() {
        viewModelScope.launch {
            mtgUiState = HomeMtgUiState.Loading
            mtgUiState = try {
                HomeMtgUiState.Success(mtg.getMonitoring().data)
            } catch (e: IOException) {
                HomeMtgUiState.Error

            } catch (e: HttpException) {
                HomeMtgUiState.Error
            }
        }
    }

    fun deleteMtg(id_monitoring: Int) {
        viewModelScope.launch {
            try {
                mtg.deleteMonitoring(id_monitoring)
            } catch (e: IOException) {
                HomeKdgUiState.Error
            } catch (e: HttpException) {
                HomeKdgUiState.Error
            }
        }
    }
}