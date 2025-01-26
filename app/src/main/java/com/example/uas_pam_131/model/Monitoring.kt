package com.example.uas_pam_131.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "monitoring",
    foreignKeys = [
        ForeignKey(
            entity = Hewan::class,
            parentColumns = ["nama_hewan"],
            childColumns = ["nama_hewan"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Kandang::class,
            parentColumns = ["id_kandang"],
            childColumns = ["id_kandang"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Petugas::class,
            parentColumns = ["nama_petugas"],
            childColumns = ["nama_petugas"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
@Serializable
data class Monitoring(
    @PrimaryKey(autoGenerate = true)
    val id_monitoring: Int = 0,
    val id_kandang: Int = 0,
    val nama_petugas: String,
    val nama_hewan: String,
    val tanggal_monitoring: String,
    val hewan_sakit: String,
    val hewan_sehat: String,
    val status: String
)

@Serializable
data class MonitoringResponse(
    val status: Boolean,
    val message: String,
    val data: List<Monitoring>

)

@Serializable
data class MonitoringResponseDetail(
    val status: Boolean,
    val message: String,
    val data: Monitoring
)