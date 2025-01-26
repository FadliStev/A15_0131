package com.example.uas_pam_131.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.uas_pam_131.KebunBinatangApplication
import com.example.uas_pam_131.ui.viewmodel.hewan.DetailHwnViewModel
import com.example.uas_pam_131.ui.viewmodel.hewan.HomeHwnViewModel
import com.example.uas_pam_131.ui.viewmodel.hewan.InsertHwnViewModel
import com.example.uas_pam_131.ui.viewmodel.hewan.UpdateHwnViewModel
import com.example.uas_pam_131.ui.viewmodel.kandang.DetailKdgViewModel
import com.example.uas_pam_131.ui.viewmodel.kandang.HomeKdgViewModel
import com.example.uas_pam_131.ui.viewmodel.kandang.InsertKdgViewModel
import com.example.uas_pam_131.ui.viewmodel.kandang.UpdateKdgViewModel
import com.example.uas_pam_131.ui.viewmodel.monitoring.DetailMtgViewModel
import com.example.uas_pam_131.ui.viewmodel.monitoring.HomeMtgViewModel
import com.example.uas_pam_131.ui.viewmodel.monitoring.InsertMtgViewModel
import com.example.uas_pam_131.ui.viewmodel.monitoring.UpdateMtgViewModel
import com.example.uas_pam_131.ui.viewmodel.petugas.DetailPtgsViewModel
import com.example.uas_pam_131.ui.viewmodel.petugas.HomePtgsViewModel
import com.example.uas_pam_131.ui.viewmodel.petugas.InsertPtgsViewModel
import com.example.uas_pam_131.ui.viewmodel.petugas.UpdatePtgsViewModel

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer { HomePtgsViewModel(aplikasiKebunBinatang().container.petugasRepository) }
        initializer { InsertPtgsViewModel(aplikasiKebunBinatang().container.petugasRepository) }
        initializer { DetailPtgsViewModel(aplikasiKebunBinatang().container.petugasRepository) }
        initializer { UpdatePtgsViewModel(createSavedStateHandle(),aplikasiKebunBinatang().container.petugasRepository) }

        initializer { HomeHwnViewModel(aplikasiKebunBinatang().container.hewanRepository) }
        initializer { InsertHwnViewModel(aplikasiKebunBinatang().container.hewanRepository) }
        initializer { DetailHwnViewModel(aplikasiKebunBinatang().container.hewanRepository) }
        initializer { UpdateHwnViewModel(createSavedStateHandle(),aplikasiKebunBinatang().container.hewanRepository) }

        initializer { HomeKdgViewModel(aplikasiKebunBinatang().container.kandangRepository) }
        initializer { InsertKdgViewModel(aplikasiKebunBinatang().container.kandangRepository, aplikasiKebunBinatang().container.hewanRepository) }
        initializer { DetailKdgViewModel(aplikasiKebunBinatang().container.kandangRepository) }
        initializer { UpdateKdgViewModel(createSavedStateHandle(),aplikasiKebunBinatang().container.kandangRepository) }

        initializer { HomeMtgViewModel(aplikasiKebunBinatang().container.monitroingRepository) }
        initializer { InsertMtgViewModel(
            aplikasiKebunBinatang().container.monitroingRepository,
            aplikasiKebunBinatang().container.kandangRepository,
            aplikasiKebunBinatang().container.hewanRepository,
            aplikasiKebunBinatang().container.petugasRepository
            ) }
        initializer { DetailMtgViewModel(aplikasiKebunBinatang().container.monitroingRepository) }
        initializer { UpdateMtgViewModel(createSavedStateHandle(),aplikasiKebunBinatang().container.monitroingRepository) }
    }
}

fun CreationExtras.aplikasiKebunBinatang(): KebunBinatangApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KebunBinatangApplication)