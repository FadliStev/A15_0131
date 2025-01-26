package com.example.uas_pam_131.ui.view.monitoring

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_pam_131.ui.navigation.DestinasiDetailKdg
import com.example.uas_pam_131.ui.navigation.DestinasiDetailMtg
import com.example.uas_pam_131.ui.viewmodel.PenyediaViewModel
import com.example.uas_pam_131.ui.viewmodel.kandang.DetailKdgViewModel
import com.example.uas_pam_131.ui.viewmodel.monitoring.DetailMtgViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenMtg(
    id_monitoring: Int,
    onEditClick: (String) -> Unit = { },
    onDeleteClick: (String) -> Unit = { },
    onBackClick: () -> Unit = { },
    modifier: Modifier = Modifier,
    viewModel: DetailMtgViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val monitoring = viewModel.mtgUiState.detailMtgUiEvent

    LaunchedEffect(id_monitoring) {
        viewModel.fetchDetailMonitoring(id_monitoring)
    }

    val isLoading = viewModel.mtgUiState.isLoading
    val isError = viewModel.mtgUiState.isError
    val errorMessage = viewModel.mtgUiState.errorMessage

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(DestinasiDetailMtg.titleRes) },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEditClick(monitoring.id_monitoring.toString()) },
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit Data")
            }
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                else if (isError) {
                    Text(
                        text = errorMessage,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else if (viewModel.mtgUiState.isUiEventNotEmpty) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                // Use Row for each detail with label and value aligned
                                DetailRowMtg(label = "ID Monitoring", value = monitoring.id_monitoring.toString())
                                DetailRowMtg(label = "ID Kandang", value = monitoring.id_kandang.toString())
                                DetailRowMtg(label = "Nama Petugas", value = monitoring.nama_petugas)
                                DetailRowMtg(label = "Nama Hewan", value = monitoring.nama_hewan)
                                DetailRowMtg(label = "Tanggal Monitoring", value = monitoring.tanggal_monitoring)
                                DetailRowMtg(label = "Hewan Sakit", value = monitoring.hewan_sakit)
                                DetailRowMtg(label = "Hewan Sehat", value = monitoring.hewan_sehat)
                                DetailRowMtg(label = "Status", value = monitoring.status)

                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun DetailRowMtg(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = ": $value",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(2f)
        )
    }
}