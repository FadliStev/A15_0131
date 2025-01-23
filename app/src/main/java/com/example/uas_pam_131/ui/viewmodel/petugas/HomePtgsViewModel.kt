package com.example.uas_pam_131.ui.viewmodel.petugas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.uas_pam_131.model.Petugas
import com.example.uas_pam_131.repository.PetugasRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeUiState{
    data class Success(val petugas: List<Petugas>): HomeUiState()
    object Error: HomeUiState()
    object Loading: HomeUiState()

}

class HomePtgsViewModel(
    private val ptgs: PetugasRepository
): ViewModel(){
    var ptgsUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getPtgs()
    }

    fun getPtgs(){
        viewModelScope.launch {
            ptgsUiState = HomeUiState.Loading
            ptgsUiState = try {
                HomeUiState.Success(ptgs.getPetugas().data)
            }catch (e: IOException){
                HomeUiState.Error

            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
    fun deletePtgs(id_petugas: Int){
        viewModelScope.launch {
            try {
                ptgs.deletePetugas(id_petugas)
            }catch (e: IOException){
                HomeUiState.Error
            }catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}