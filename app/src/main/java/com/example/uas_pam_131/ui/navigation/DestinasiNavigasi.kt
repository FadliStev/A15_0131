package com.example.uas_pam_131.ui.navigation

interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}

object DestinasiHome : DestinasiNavigasi{
    override val route = "Dashboard"
    override val titleRes = "Aplikasi Kebun Binatang"
}

object DestinasiHomePtgs: DestinasiNavigasi{
    override val route = "HomePtgs"
    override val titleRes = "Home Ptgs"
}

object DestinasiInsertPtgs: DestinasiNavigasi{
    override val route= "item_entryPtgs"
    override val titleRes = "Entry Ptgs"

}

object DestinasiDetailPtgs : DestinasiNavigasi {
    override val route = "detail/{id_petugas}"
    override val titleRes = "Detail Petugas"
    const val ID_PETUGAS = "id_petugas"
    val routeWithArgs = "$route/{$ID_PETUGAS}"
}

object DestinasiUpdatePtgs: DestinasiNavigasi {
    override val route = "updatePtgs"
    override val titleRes = "Update Petugas"
    const val ID_PETUGAS = "id_petugas"
    val routesWithArg = "$route/{$ID_PETUGAS}"
}

object DestinasiHomeHwn: DestinasiNavigasi{
    override val route = "HomeHwn"
    override val titleRes = "Home Hewan"
}

object DestinasiInsertHwn: DestinasiNavigasi{
    override val route= "item_entryHwn"
    override val titleRes = "Entry Hewan"

}

object DestinasiDetailHwn : DestinasiNavigasi {
    override val route = "detail/{id_hewan}"
    override val titleRes = "Detail Hewan"
    const val ID_HEWAN = "id_hewan"
    val routeWithArgs = "$route/{$ID_HEWAN}"
}

object DestinasiUpdateHwn: DestinasiNavigasi {
    override val route = "updateHwn"
    override val titleRes = "Update Hewan"
    const val ID_HEWAN = "id_hewan"
    val routesWithArg = "$route/{$ID_HEWAN}"
}

object DestinasiHomeKdg: DestinasiNavigasi{
    override val route = "HomeKdg"
    override val titleRes = "Home Kandang"
}

object DestinasiInsertKdg: DestinasiNavigasi{
    override val route= "item_entryKdg"
    override val titleRes = "Entry Kandang"

}

object DestinasiDetailKdg : DestinasiNavigasi {
    override val route = "detail/{id_kandang}"
    override val titleRes = "Detail Kandang"
    const val ID_KANDANG = "id_kandang"
    val routeWithArgs = "$route/{$ID_KANDANG}"
}

object DestinasiUpdateKdg: DestinasiNavigasi {
    override val route = "updateKdg"
    override val titleRes = "Update Kandang"
    const val ID_KANDANG = "id_kandang"
    val routesWithArg = "$route/{$ID_KANDANG}"
}

