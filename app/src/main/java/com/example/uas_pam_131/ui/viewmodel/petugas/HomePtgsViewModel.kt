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

sealed class HomePtgsUiState{
    data class Success(val petugas: List<Petugas>): HomePtgsUiState()
    object Error: HomePtgsUiState()
    object Loading: HomePtgsUiState()

}

class HomePtgsViewModel(
    private val ptgs: PetugasRepository
): ViewModel(){
    var ptgsUiState: HomePtgsUiState by mutableStateOf(HomePtgsUiState.Loading)
        private set

    init {
        getPtgs()
    }

    fun getPtgs(){
        viewModelScope.launch {
            ptgsUiState = HomePtgsUiState.Loading
            ptgsUiState = try {
                HomePtgsUiState.Success(ptgs.getPetugas().data)
            }catch (e: IOException){
                HomePtgsUiState.Error

            }catch (e: HttpException){
                HomePtgsUiState.Error
            }
        }
    }
    fun deletePtgs(id_petugas: Int){
        viewModelScope.launch {
            try {
                ptgs.deletePetugas(id_petugas)
            }catch (e: IOException){
                HomePtgsUiState.Error
            }catch (e: HttpException){
                HomePtgsUiState.Error
            }
        }
    }
}