package com.example.uas_pam_131.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uas_pam_131.ui.view.petugas.DetailScreen
import com.example.uas_pam_131.ui.view.petugas.EntryMhsScreen
import com.example.uas_pam_131.ui.view.petugas.HomeScreen
import com.example.uas_pam_131.ui.view.petugas.UpdateScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHomePtgs.route,
        modifier = Modifier
    ) {
        composable(DestinasiHomePtgs.route) {
            HomeScreen(
                navigateToltemEntry = { navController.navigate(DestinasiInsertPtgs.route) },
                onDetailClick = {id_petugas ->
                    navController.navigate("${DestinasiDetailPtgs.route}/$id_petugas")
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
    }
}