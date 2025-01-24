package com.example.uas_pam_131.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "hewan",
    indices = [Index(value = ["nama_hewan"], unique = true)]
)
@Serializable
data class Hewan(
    @PrimaryKey(autoGenerate = true)
    val id_hewan: Int = 0,
    val nama_hewan: String,
    val tipe_pakan: String,
    val populasi: String,
    val zona_wilayah: String
)

@Serializable
data class HewanResponse(
    val status: Boolean,
    val message: String,
    val data: List<Hewan>

)

@Serializable
data class HewanResponseDetail(
    val status: Boolean,
    val message: String,
    val data: Hewan
)