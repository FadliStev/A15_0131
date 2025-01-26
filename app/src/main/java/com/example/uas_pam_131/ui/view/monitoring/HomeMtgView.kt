package com.example.uas_pam_131.ui.view.monitoring

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_pam_131.R
import com.example.uas_pam_131.model.Kandang
import com.example.uas_pam_131.model.Monitoring
import com.example.uas_pam_131.ui.customwidget.CostumeTopAppBar
import com.example.uas_pam_131.ui.navigation.DestinasiHomeKdg
import com.example.uas_pam_131.ui.navigation.DestinasiHomeMtg
import com.example.uas_pam_131.ui.viewmodel.PenyediaViewModel
import com.example.uas_pam_131.ui.viewmodel.kandang.HomeKdgUiState
import com.example.uas_pam_131.ui.viewmodel.kandang.HomeKdgViewModel
import com.example.uas_pam_131.ui.viewmodel.monitoring.HomeMtgUiState
import com.example.uas_pam_131.ui.viewmodel.monitoring.HomeMtgViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenMtg(
    navigateToltemEntry: ()-> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    viewModel: HomeMtgViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomeMtg.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getMtg()
                },
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToltemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Hewan")
            }
        },
    ) {innerPadding ->
        HomeStatusMtg(
            homeMtgUiState = viewModel.mtgUiState,
            retryAction = { viewModel.getMtg() }, modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteMtg(it.id_monitoring)
                viewModel.getMtg()
            }
        )

    }
}

@Composable
fun HomeStatusMtg(
    homeMtgUiState: HomeMtgUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Monitoring) -> Unit = {},
    onDetailClick: (Int) -> Unit
){
    when (homeMtgUiState){
        is HomeMtgUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())


        is HomeMtgUiState.Success ->
            if (homeMtgUiState.monitoring.isEmpty()){
                return Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(text = "Tidak ada data Kandang")
                }
            }else{
                MtgLayout(
                    monitoring = homeMtgUiState.monitoring,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.id_monitoring)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is HomeMtgUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier){
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.network_error), contentDescription = "")
        Text(text = stringResource(id = R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(id = R.string.retry))
        }
    }
}

@Composable
fun MtgLayout(
    monitoring: List<Monitoring>,
    modifier: Modifier = Modifier,
    onDetailClick: (Monitoring) -> Unit,
    onDeleteClick: (Monitoring) -> Unit = {}
){
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(monitoring){ monitoring ->
            MtgCard(
                monitoring = monitoring,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(monitoring) },
                onDeleteClick = {
                    onDeleteClick(monitoring)
                }
            )
        }
    }
}

@Composable
fun MtgCard(
    monitoring: Monitoring,
    modifier: Modifier = Modifier,
    onDeleteClick:(Monitoring) -> Unit = {}
){
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = monitoring.nama_petugas,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(Modifier.weight(1f))
                IconButton(onClick = {onDeleteClick(monitoring)}) {
                    Icon(imageVector = Icons.Default.Delete,
                        contentDescription = null)
                }


            }
            Text(
                text = monitoring.nama_hewan,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = monitoring.tanggal_monitoring,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = monitoring.hewan_sakit,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = monitoring.hewan_sehat,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = monitoring.status,
                style = MaterialTheme.typography.titleSmall
            )


        }
    }
}