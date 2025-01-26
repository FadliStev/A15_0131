package com.example.uas_pam_131.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uas_pam_131.ui.view.HomeView
import com.example.uas_pam_131.ui.view.hewan.DetailScreenHwn
import com.example.uas_pam_131.ui.view.hewan.EntryHwnScreen
import com.example.uas_pam_131.ui.view.hewan.HomeScreenHwn
import com.example.uas_pam_131.ui.view.hewan.UpdateScreenHwn
import com.example.uas_pam_131.ui.view.kandang.DetailScreenKdg
import com.example.uas_pam_131.ui.view.kandang.EntryKdgScreen
import com.example.uas_pam_131.ui.view.kandang.HomeScreenKdg
import com.example.uas_pam_131.ui.view.kandang.UpdateScreenKdg
import com.example.uas_pam_131.ui.view.monitoring.DetailScreenMtg
import com.example.uas_pam_131.ui.view.monitoring.EntryMtgScreen
import com.example.uas_pam_131.ui.view.monitoring.HomeScreenMtg
import com.example.uas_pam_131.ui.view.monitoring.UpdateScreenMtg
import com.example.uas_pam_131.ui.view.petugas.DetailScreen
import com.example.uas_pam_131.ui.view.petugas.EntryMhsScreen
import com.example.uas_pam_131.ui.view.petugas.HomeScreen
import com.example.uas_pam_131.ui.view.petugas.UpdateScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(
            route = DestinasiHome.route
        ) {
            HomeView(
                modifier = Modifier,
                onNavigatePetugas = {
                    navController.navigate(DestinasiHomePtgs.route)
                },
                onNavigateHewan = {
                    navController.navigate(DestinasiHomeHwn.route)
                },
                onNavigateMonitoring = {
                    navController.navigate(DestinasiHomeMtg.route)
                },
                onNavigateKandang = {
                    navController.navigate(DestinasiHomeKdg.route)
                },
                onBack = {

                }
            )
        }

        composable(DestinasiHomePtgs.route) {
            HomeScreen(
                navigateToltemEntry = { navController.navigate(DestinasiInsertPtgs.route) },
                onDetailClick = {id_petugas ->
                    navController.navigate("${DestinasiDetailPtgs.route}/$id_petugas")
                },
                navigateBack = {
                    navController.navigate(DestinasiHome.route)
                }
            )
        }

        composable(DestinasiInsertPtgs.route) {
            EntryMhsScreen(navigateBack = {
                navController.navigate(DestinasiHomePtgs.route) {
                    popUpTo(DestinasiHomePtgs.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DestinasiDetailPtgs.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailPtgs.ID_PETUGAS) {
                    type = NavType.StringType
                }
            )
        ) {
            val id_petugas = it.arguments?.getString(DestinasiDetailPtgs.ID_PETUGAS)
            id_petugas?.let { id_petugas ->
                DetailScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdatePtgs.route}/$id_petugas")
                    },
                    id_petugas = id_petugas.toInt(),
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            route = DestinasiUpdatePtgs.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdatePtgs.ID_PETUGAS) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id_petugas = backStackEntry.arguments?.getString(DestinasiUpdatePtgs.ID_PETUGAS)
            id_petugas?.let {
                UpdateScreen(
                    onBack = { navController.popBackStack() },
                    onNavigate = {
                        navController.navigate(DestinasiHomePtgs.route) {
                            popUpTo(DestinasiHomePtgs.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                )
            }
        }

        composable(DestinasiHomeHwn.route) {
            HomeScreenHwn(
                navigateToltemEntry = { navController.navigate(DestinasiInsertHwn.route) },
                onDetailClick = {id_hewan ->
                    navController.navigate("${DestinasiDetailHwn.route}/$id_hewan")
                },
                navigateBack = {
                    navController.navigate(DestinasiHome.route)
                }
            )
        }

        composable(DestinasiInsertHwn.route) {
            EntryHwnScreen(navigateBack = {
                navController.navigate(DestinasiHomeHwn.route) {
                    popUpTo(DestinasiHomeHwn.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DestinasiDetailHwn.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailHwn.ID_HEWAN) {
                    type = NavType.StringType
                }
            )
        ) {
            val id_hewan = it.arguments?.getString(DestinasiDetailHwn.ID_HEWAN)
            id_hewan?.let { id_hewan ->
                DetailScreenHwn(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateHwn.route}/$id_hewan")
                    },
                    id_hewan = id_hewan.toInt(),
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            route = DestinasiUpdateHwn.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateHwn.ID_HEWAN) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id_hewan = backStackEntry.arguments?.getString(DestinasiUpdateHwn.ID_HEWAN)
            id_hewan?.let {
                UpdateScreenHwn(
                    onBack = { navController.popBackStack() },
                    onNavigate = {
                        navController.navigate(DestinasiHomeHwn.route) {
                            popUpTo(DestinasiHomeHwn.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                )
            }
        }
        composable(DestinasiHomeKdg.route) {
            HomeScreenKdg(
                navigateToltemEntry = { navController.navigate(DestinasiInsertKdg.route) },
                onDetailClick = {id_kandang ->
                    navController.navigate("${DestinasiDetailKdg.route}/$id_kandang")
                },
                navigateBack = {
                    navController.navigate(DestinasiHome.route)
                }
            )
        }

        composable(DestinasiInsertKdg.route) {
            EntryKdgScreen(navigateBack = {
                navController.navigate(DestinasiHomeKdg.route) {
                    popUpTo(DestinasiHomeKdg.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DestinasiDetailKdg.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailKdg.ID_KANDANG) {
                    type = NavType.StringType
                }
            )
        ) {
            val id_kandang = it.arguments?.getString(DestinasiDetailKdg.ID_KANDANG)
            id_kandang?.let { id_kandang ->
                DetailScreenKdg(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateKdg.route}/$id_kandang")
                    },
                    id_kandang = id_kandang.toInt(),
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            route = DestinasiUpdateKdg.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateKdg.ID_KANDANG) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id_kandang = backStackEntry.arguments?.getString(DestinasiUpdateKdg.ID_KANDANG)
            id_kandang?.let {
                UpdateScreenKdg(
                    onBack = { navController.popBackStack() },
                    onNavigate = {
                        navController.navigate(DestinasiHomeKdg.route) {
                            popUpTo(DestinasiHomeKdg.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                )
            }
        }

        composable(DestinasiHomeMtg.route) {
            HomeScreenMtg(
                navigateToltemEntry = { navController.navigate(DestinasiInsertMtg.route) },
                onDetailClick = {id_monitoring ->
                    navController.navigate("${DestinasiDetailMtg.route}/$id_monitoring")
                },
                navigateBack = {
                    navController.navigate(DestinasiHome.route)
                }
            )
        }

        composable(DestinasiInsertMtg.route) {
            EntryMtgScreen(navigateBack = {
                navController.navigate(DestinasiHomeMtg.route) {
                    popUpTo(DestinasiHomeMtg.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DestinasiDetailMtg.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailMtg.ID_MONITORING) {
                    type = NavType.StringType
                }
            )
        ) {
            val id_monitoring = it.arguments?.getString(DestinasiDetailMtg.ID_MONITORING)
            id_monitoring?.let { id_monitoring ->
                DetailScreenMtg(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateMtg.route}/$id_monitoring")
                    },
                    id_monitoring = id_monitoring.toInt(),
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            route = DestinasiUpdateMtg.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateMtg.ID_MONITORING) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id_monitoring = backStackEntry.arguments?.getString(DestinasiUpdateMtg.ID_MONITORING)
            id_monitoring?.let {
                UpdateScreenMtg(
                    onBack = { navController.popBackStack() },
                    onNavigate = {
                        navController.navigate(DestinasiHomeMtg.route) {
                            popUpTo(DestinasiHomeMtg.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                )
            }
        }
    }
}