package com.example.uas_pam_131.ui.viewmodel.kandang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.uas_pam_131.model.Hewan
import com.example.uas_pam_131.model.Kandang
import com.example.uas_pam_131.repository.HewanRepository
import com.example.uas_pam_131.repository.KandangRepository
import com.example.uas_pam_131.ui.viewmodel.hewan.HomeHwnUiState
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeKdgUiState{
    data class Success(val kandang: List<Kandang>): HomeKdgUiState()
    object Error: HomeKdgUiState()
    object Loading: HomeKdgUiState()

}

class HomeKdgViewModel(
    private val kdg: KandangRepository
): ViewModel(){
    var kdgUiState: HomeKdgUiState by mutableStateOf(HomeKdgUiState.Loading)
        private set

    init {
        getKdg()
    }

    fun getKdg(){
        viewModelScope.launch {
            kdgUiState = HomeKdgUiState.Loading
            kdgUiState = try {
                HomeKdgUiState.Success(kdg.getKandang().data)
            }catch (e: IOException){
                HomeKdgUiState.Error

            }catch (e: HttpException){
                HomeKdgUiState.Error
            }
        }
    }
    fun deleteKdg(id_kandang: Int){
        viewModelScope.launch {
            try {
                kdg.deleteKandang(id_kandang)
            }catch (e: IOException){
                HomeKdgUiState.Error
            }catch (e: HttpException){
                HomeKdgUiState.Error
            }
        }
    }
}