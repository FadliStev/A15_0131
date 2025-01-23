package com.example.uas_pam_131.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.uas_pam_131.KebunBinatangApplication
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
    }
}

fun CreationExtras.aplikasiKebunBinatang(): KebunBinatangApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KebunBinatangApplication)