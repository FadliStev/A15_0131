package com.example.uas_pam_131.ui.viewmodel.monitoring

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam_131.model.Hewan
import com.example.uas_pam_131.model.HewanResponse
import com.example.uas_pam_131.model.Kandang
import com.example.uas_pam_131.model.KandangResponse
import com.example.uas_pam_131.model.Monitoring
import com.example.uas_pam_131.model.Petugas
import com.example.uas_pam_131.model.PetugasResponse
import com.example.uas_pam_131.repository.HewanRepository
import com.example.uas_pam_131.repository.KandangRepository
import com.example.uas_pam_131.repository.MonitroingRepository
import com.example.uas_pam_131.repository.PetugasRepository
import kotlinx.coroutines.launch

class InsertMtgViewModel(
    private val mtg: MonitroingRepository,
    private val kdg: KandangRepository,
    private val hwn: HewanRepository,
    private val ptgs: PetugasRepository
): ViewModel(){

    var mtgUiState by mutableStateOf(InsertMtgUiState())
        private set

    init {
        loadHwnNKdgNPtgs()
    }

    private fun loadHwnNKdgNPtgs(){
        viewModelScope.launch {
            try {
                val kandangResponse: KandangResponse = kdg.getKandang()
                val kandangList: List<Kandang> = kandangResponse.data

                val petugasResponse: PetugasResponse = ptgs.getPetugas()
                val petugasList: List<Petugas> = petugasResponse.data

                val hewanResponse: HewanResponse = hwn.getHewan()
                val hewanList: List<Hewan> = hewanResponse.data

                mtgUiState = mtgUiState.copy(
                    hewanOption = hewanList.map {
                        it.toDropdownOptionHwn()
                    },
                    kandangOption = kandangList.map {
                        it.toDropdownOptionKdg()
                    },
                    petugasOption = petugasList.map {
                        it.toDropdownOptionPtgs()
                    }
                )

            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun updateInsertMtgState(insertMtgUiEvent: InsertMtgUiEvent){
        mtgUiState = InsertMtgUiState(insertMtgUiEvent = insertMtgUiEvent)
    }

    suspend fun insertMtg(){
        viewModelScope.launch {
            try {
                mtg.insertMonitoring(mtgUiState.insertMtgUiEvent.toMtg())
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }


}

data class InsertMtgUiState(
    val insertMtgUiEvent: InsertMtgUiEvent = InsertMtgUiEvent(),
    val hewanOption: List<DropdownOptionHwn> = emptyList(),
    val kandangOption: List<DropdownOptionKdg> = emptyList(),
    val petugasOption: List<DropdownOptionPtgs> = emptyList()
)

data class InsertMtgUiEvent(
    val id_monitoring: Int = 0,
    val id_kandang: Int = 0,
    val nama_petugas: String = "",
    val nama_hewan: String = "",
    val tanggal_monitoring: String = "",
    val hewan_sakit: String = "",
    val hewan_sehat: String = "",
    val status: String = ""
)

fun InsertMtgUiEvent.toMtg(): Monitoring = Monitoring(
    id_monitoring = id_monitoring,
    id_kandang = id_kandang,
    nama_petugas = nama_petugas,
    nama_hewan = nama_hewan,
    tanggal_monitoring = tanggal_monitoring,
    hewan_sakit = hewan_sakit,
    hewan_sehat = hewan_sehat,
    status = status
)

fun Monitoring.toUiStateMtg(): InsertMtgUiState = InsertMtgUiState(
    insertMtgUiEvent = toInsertMtgUiEvent()
)

fun Monitoring.toInsertMtgUiEvent(): InsertMtgUiEvent = InsertMtgUiEvent(
    id_monitoring = id_monitoring,
    id_kandang = id_kandang,
    nama_petugas = nama_petugas,
    nama_hewan = nama_hewan,
    tanggal_monitoring = tanggal_monitoring,
    hewan_sakit = hewan_sakit,
    hewan_sehat = hewan_sehat,
    status = status
)
data class DropdownOptionKdg(
    val id_kandang: String,
    val label: String
)

data class DropdownOptionHwn(
    val nama_hewan: String,
    val label: String
)

data class DropdownOptionPtgs(
    val nama_petugas: String,
    val label: String
)

fun Hewan.toDropdownOptionHwn() = DropdownOptionHwn(
    nama_hewan = nama_hewan,
    label = nama_hewan
)

fun Kandang.toDropdownOptionKdg() = DropdownOptionKdg(
    id_kandang = id_kandang.toString(),
    label = id_kandang.toString()
)

fun Petugas.toDropdownOptionPtgs() = DropdownOptionPtgs(
    nama_petugas = nama_petugas,
    label = nama_petugas
)

