package com.example.uas_pam_131.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.uas_pam_131.KebunBinatangApplication
import com.example.uas_pam_131.ui.viewmodel.petugas.DetailPetugasViewModel
import com.example.uas_pam_131.ui.viewmodel.petugas.HomePetugasViewModel
import com.example.uas_pam_131.ui.viewmodel.petugas.InsertPetugasViewModel
import com.example.uas_pam_131.ui.viewmodel.petugas.UpdatePetugasViewModel

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer { HomePetugasViewModel(aplikasiKebunBinatang().container.petugasRepository) }
        initializer { InsertPetugasViewModel(aplikasiKebunBinatang().container.petugasRepository) }
        initializer { DetailPetugasViewModel(aplikasiKebunBinatang().container.petugasRepository) }
        initializer { UpdatePetugasViewModel(createSavedStateHandle(),aplikasiKebunBinatang().container.petugasRepository) }
    }
}

fun CreationExtras.aplikasiKebunBinatang(): KebunBinatangApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KebunBinatangApplication)