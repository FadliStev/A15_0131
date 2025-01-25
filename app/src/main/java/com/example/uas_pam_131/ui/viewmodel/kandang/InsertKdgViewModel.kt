package com.example.uas_pam_131.ui.viewmodel.kandang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_pam_131.model.Hewan
import com.example.uas_pam_131.model.HewanResponse
import com.example.uas_pam_131.model.Kandang
import com.example.uas_pam_131.repository.HewanRepository
import com.example.uas_pam_131.repository.KandangRepository
import kotlinx.coroutines.launch

class InsertKdgViewModel(
    private val kdg: KandangRepository,
    private val hwn: HewanRepository
): ViewModel(){

    var kdgUiState by mutableStateOf(InsertKdgUiState())
        private set

    init {
        loadHewan()
    }

    private fun loadHewan(){
        viewModelScope.launch {
            try {
                val hewanResponse: HewanResponse = hwn.getHewan()
                val hewanList: List<Hewan> = hewanResponse.data

                kdgUiState = kdgUiState.copy(
                    hewanOption = hewanList.map {
                        it.toDropdownOption()
                    }
                )
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun updateInsertKdgState(insertKdgUiEvent: InsertKdgUiEvent){
        kdgUiState = InsertKdgUiState(insertKdgUiEvent = insertKdgUiEvent)
    }

    suspend fun insertKdg(){
        viewModelScope.launch {
            try {
                kdg.insertKandang(kdgUiState.insertKdgUiEvent.toKdg())
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }


}

data class InsertKdgUiState(
    val insertKdgUiEvent: InsertKdgUiEvent = InsertKdgUiEvent(),
    val hewanOption: List<DropdownOption> = emptyList()
)

data class InsertKdgUiEvent(
    val id_kandang: Int = 0,
    val nama_hewan: String = "",
    val kapasitas: String = "",
    val lokasi: String = ""
)

fun InsertKdgUiEvent.toKdg(): Kandang = Kandang(
    id_kandang = id_kandang,
    nama_hewan = nama_hewan,
    kapasitas = kapasitas,
    lokasi = lokasi
)

fun Kandang.toUiStateKdg(): InsertKdgUiState = InsertKdgUiState(
    insertKdgUiEvent = toInsertKdgUiEvent()
)

fun Kandang.toInsertKdgUiEvent(): InsertKdgUiEvent = InsertKdgUiEvent(
    id_kandang = id_kandang,
    nama_hewan = nama_hewan,
    kapasitas = kapasitas,
    lokasi = lokasi,

)

data class DropdownOption(
    val nama_hewan: String,
    val label: String
)

fun Hewan.toDropdownOption() = DropdownOption(
    nama_hewan = nama_hewan,
    label = nama_hewan
)