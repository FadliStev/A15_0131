package com.example.uas_pam_131.ui.viewmodel.hewan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.uas_pam_131.model.Hewan
import com.example.uas_pam_131.model.Kandang
import com.example.uas_pam_131.repository.HewanRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeHwnUiState{
    data class Success(val hewan: List<Hewan>): HomeHwnUiState()
    object Error: HomeHwnUiState()
    object Loading: HomeHwnUiState()

}

class HomeHwnViewModel(
    private val hwn: HewanRepository
): ViewModel(){
    var hwnUiState: HomeHwnUiState by mutableStateOf(HomeHwnUiState.Loading)
        private set


    init {
        getHwn()
    }

    fun getHwn(){
        viewModelScope.launch {
            hwnUiState = HomeHwnUiState.Loading
            hwnUiState = try {
                HomeHwnUiState.Success(hwn.getHewan().data)
            }catch (e: IOException){
                HomeHwnUiState.Error

            }catch (e: HttpException){
                HomeHwnUiState.Error
            }
        }

    }
    fun deleteHwn(id_hewan: Int){
        viewModelScope.launch {
            try {
                hwn.deleteHewan(id_hewan)
            }catch (e: IOException){
                HomeHwnUiState.Error
            }catch (e: HttpException){
                HomeHwnUiState.Error
            }
        }
    }
}

