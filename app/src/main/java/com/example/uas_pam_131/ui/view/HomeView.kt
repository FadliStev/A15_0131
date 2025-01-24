package com.example.uas_pam_131.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.uas_pam_131.ui.customwidget.CostumeTopAppBar
import com.example.uas_pam_131.ui.customwidget.DashboardTopAppBar
import com.example.uas_pam_131.ui.navigation.DestinasiHome
import com.example.uas_pam_131.ui.navigation.DestinasiNavigasi
import com.example.uas_pam_131.ui.navigation.DestinasiUpdatePtgs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    onNavigatePetugas: () -> Unit,
    onNavigateHewan: () -> Unit,
    onNavigateKandang: () -> Unit,
    onNavigateMonitoring: () -> Unit,
    onBack: () -> Unit,
) {

    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DashboardTopAppBar()
        }
    ) { innerPadding ->
        BodyHome(
            modifier = Modifier.padding(innerPadding),
            onPetugasClick = {
                onNavigatePetugas()
            },
            onHewanClick = {
                onNavigateHewan()
            },
            onKandangClick = {
                onNavigateKandang()
            },
            onMonitoringClick = {
                onNavigateMonitoring()
            }
        )
    }
}

@Composable
fun BodyHome(
    modifier: Modifier = Modifier,
    onPetugasClick: () -> Unit = {},
    onHewanClick: () -> Unit = {},
    onKandangClick: () -> Unit = {},
    onMonitoringClick: () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row {
            CardMenu(
                namaMenu = "Hewan",
                onClick = onHewanClick,
                namaIcon = Icons.Default.Home
            )
            CardMenu(
                namaMenu = "Petugas",
                onClick = onPetugasClick,
                namaIcon = Icons.Default.Person
            )
        }
        Row {
            CardMenu(
                namaMenu = "Kandang",
                onClick = onKandangClick,
                namaIcon = Icons.Default.Home
            )
            CardMenu(
                namaMenu = "Monitoring",
                onClick = onMonitoringClick,
                namaIcon = Icons.Default.Info
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardMenu(
    namaMenu: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    namaIcon: ImageVector
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .width(188.dp)
            .height(230.dp)
            .padding(8.dp),
    ) {
        Box(
            modifier = Modifier
                .background(color = Color(0xFF42A5F5))
                .fillMaxHeight()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Icon(
                    imageVector = namaIcon,
                    contentDescription = "",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = namaMenu,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}