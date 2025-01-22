package com.example.uas_pam_131

import android.app.Application
import com.example.uas_pam_131.di.PetugasContainer

class PetugasApplication: Application() {

    lateinit var container: PetugasContainer
    override fun onCreate() {
        super.onCreate()
        container = PetugasContainer()
    }
}