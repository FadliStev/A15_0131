package com.example.uas_pam_131.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "kandang",
    indices = [Index(value = ["id_kandang"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = Hewan::class,
            parentColumns = ["nama_hewan"],
            childColumns = ["nama_hewan"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
@Serializable
data class Kandang(
    @PrimaryKey(autoGenerate = true)
    val id_kandang: Int = 0,
    val nama_hewan: String,
    val kapasitas: String,
    val lokasi: String,
)

@Serializable
data class KandangResponse(
    val status: Boolean,
    val message: String,
    val data: List<Kandang>

)

@Serializable
data class KandangResponseDetail(
    val status: Boolean,
    val message: String,
    val data: Kandang
)