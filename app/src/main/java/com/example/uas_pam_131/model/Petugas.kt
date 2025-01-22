package com.example.uas_pam_131.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "petugas",
    indices = [Index(value = ["nama_petugas"], unique = true)]
)
@Serializable
data class Petugas(
    @PrimaryKey(autoGenerate = true)
    val id_petugas: Int = 0,
    val nama_petugas: String,
    val jabatan: String
)

@Serializable
data class PetugasResponse(
    val status: Boolean,
    val message: String,
    val data: List<Petugas>

)

@Serializable
data class PetugasResponseDetail(
    val status: Boolean,
    val message: String,
    val data: Petugas
)