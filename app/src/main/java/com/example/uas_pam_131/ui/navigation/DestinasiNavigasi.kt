package com.example.uas_pam_131.ui.navigation

interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}

object DestinasiHomePtgs: DestinasiNavigasi{
    override val route = "Home"
    override val titleRes = "Home Ptgs"
}

object DestinasiInsertPtgs: DestinasiNavigasi{
    override val route= "item_entry"
    override val titleRes = "Entry Ptgs"

}

object DestinasiDetailPtgs : DestinasiNavigasi {
    override val route = "detail/{id_petugas}"
    override val titleRes = "Detail Petugas"
    const val ID_PETUGAS = "id_petugas"
    val routeWithArgs = "$route/{$ID_PETUGAS}"
}

object DestinasiUpdatePtgs: DestinasiNavigasi {
    override val route = "update"
    override val titleRes = "Update Petugas"
    const val ID_PETUGAS = "id_petugas"
    val routesWithArg = "$route/{$ID_PETUGAS}"
}

object DestinasiHomeHwn: DestinasiNavigasi{
    override val route = "Home"
    override val titleRes = "Home Hewan"
}

object DestinasiInsertHwn: DestinasiNavigasi{
    override val route= "item_entry"
    override val titleRes = "Entry Hewan"

}

object DestinasiDetailHwn : DestinasiNavigasi {
    override val route = "detail/{id_hewan}"
    override val titleRes = "Detail Hewan"
    const val ID_HEWAN = "id_hewan"
    val routeWithArgs = "$route/{$ID_HEWAN}"
}

object DestinasiUpdateHwn: DestinasiNavigasi {
    override val route = "update"
    override val titleRes = "Update Hewan"
    const val ID_HEWAN = "id_hewan"
    val routesWithArg = "$route/{$ID_HEWAN}"
}

