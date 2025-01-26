package com.example.uas_pam_131.ui.view.monitoring

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_pam_131.ui.customwidget.CostumeTopAppBar
import com.example.uas_pam_131.ui.navigation.DestinasiInsertKdg
import com.example.uas_pam_131.ui.navigation.DestinasiInsertMtg
import com.example.uas_pam_131.ui.viewmodel.PenyediaViewModel
import com.example.uas_pam_131.ui.viewmodel.monitoring.InsertMtgUiEvent
import com.example.uas_pam_131.ui.viewmodel.monitoring.InsertMtgUiState
import com.example.uas_pam_131.ui.viewmodel.monitoring.InsertMtgViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryMtgScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertMtgViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiInsertMtg.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) {innerPadding ->
        EntryBodyMtg(
            insertMtgUiState = viewModel.mtgUiState,
            onMonitoringValueChange = viewModel::updateInsertMtgState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertMtg()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )

    }
}

@Composable
fun EntryBodyMtg(
    insertMtgUiState: InsertMtgUiState,
    onMonitoringValueChange: (InsertMtgUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputMtg(
            insertMtgUiEvent = insertMtgUiState.insertMtgUiEvent,
            onValueChange = onMonitoringValueChange,
            modifier = Modifier.fillMaxWidth())
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputMtg(
    insertMtgUiEvent: InsertMtgUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertMtgUiEvent) -> Unit = {},
    InsertMtgViewModel: InsertMtgViewModel = viewModel(factory = PenyediaViewModel.Factory),
    enabled: Boolean = true
){
    var hwnUI = InsertMtgViewModel.mtgUiState.hewanOption
    var kdgUI = InsertMtgViewModel.mtgUiState.kandangOption
    var ptgsUI = InsertMtgViewModel.mtgUiState.petugasOption

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        DropDownAll(
            title = "Pilih Id Kandang",
            options = kdgUI.map { it.id_kandang },
            selectedOption = insertMtgUiEvent.id_kandang.toString(),
            onOptionSelected = { id_kandang ->
                onValueChange(insertMtgUiEvent.copy(id_kandang = id_kandang.toInt()))

            }
        )

        DropDownAll(
            title = "Pilih Petugas",
            options = ptgsUI.map { it.nama_petugas },
            selectedOption = insertMtgUiEvent.nama_petugas,
            onOptionSelected = { nama_petugas ->
                onValueChange(insertMtgUiEvent.copy(nama_petugas = nama_petugas))

            }
        )

        DropDownAll(
            title = "Pilih Hewan",
            options = hwnUI.map { it.nama_hewan },
            selectedOption = insertMtgUiEvent.nama_hewan,
            onOptionSelected = { nama_hewan ->
                onValueChange(insertMtgUiEvent.copy(nama_hewan = nama_hewan))
            }
        )

        OutlinedTextField(
            value = insertMtgUiEvent.tanggal_monitoring,
            onValueChange = {onValueChange(insertMtgUiEvent.copy(tanggal_monitoring = it))},
            label = { Text("Tanggal Monitoring") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertMtgUiEvent.hewan_sakit,
            onValueChange = {onValueChange(insertMtgUiEvent.copy(hewan_sakit = it))},
            label = { Text("Masukkan Hewan Sakit") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertMtgUiEvent.hewan_sehat,
            onValueChange = {onValueChange(insertMtgUiEvent.copy(hewan_sehat = it))},
            label = { Text("Masukkan Hewan Sehat") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertMtgUiEvent.status,
            onValueChange = {onValueChange(insertMtgUiEvent.copy(status = it))},
            label = { Text("Masukkan Status") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )


        if (enabled){
            Text(
                text = "Isi Semua Data!",
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

@Composable
fun DropDownAll(
    title: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null
){
    var expanded by remember { mutableStateOf(false) }
    var currentSelected by remember { mutableStateOf(selectedOption) }

    Column {
        OutlinedTextField(
            value = currentSelected,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = title) },
            trailingIcon = {
                androidx.compose.material3.IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ArrowDropDown else Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ){
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        onOptionSelected(option)
                        currentSelected = option
                        expanded = false
                    }
                )
            }
        }
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red
            )

        }
    }
}
