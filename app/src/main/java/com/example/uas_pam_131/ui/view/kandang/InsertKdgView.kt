package com.example.uas_pam_131.ui.view.kandang

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
import com.example.uas_pam_131.ui.navigation.DestinasiInsertHwn
import com.example.uas_pam_131.ui.navigation.DestinasiInsertKdg
import com.example.uas_pam_131.ui.viewmodel.PenyediaViewModel
import com.example.uas_pam_131.ui.viewmodel.hewan.HomeHwnViewModel
import com.example.uas_pam_131.ui.viewmodel.hewan.InsertHwnUiEvent
import com.example.uas_pam_131.ui.viewmodel.hewan.InsertHwnUiState
import com.example.uas_pam_131.ui.viewmodel.hewan.InsertHwnViewModel
import com.example.uas_pam_131.ui.viewmodel.kandang.InsertKdgUiEvent
import com.example.uas_pam_131.ui.viewmodel.kandang.InsertKdgUiState
import com.example.uas_pam_131.ui.viewmodel.kandang.InsertKdgViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.serializer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryKdgScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertKdgViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiInsertKdg.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) {innerPadding ->
        EntryBodyKdg(
            insertKdgUiState = viewModel.kdgUiState,
            onHewanValueChange = viewModel::updateInsertKdgState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertKdg()
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
fun EntryBodyKdg(
    insertKdgUiState: InsertKdgUiState,
    onHewanValueChange: (InsertKdgUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputKdg(
            insertKdgUiEvent = insertKdgUiState.insertKdgUiEvent,
            onValueChange = onHewanValueChange,
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
fun FormInputKdg(
    insertKdgUiEvent: InsertKdgUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertKdgUiEvent) -> Unit = {},
    InsertKdgViewModel: InsertKdgViewModel = viewModel(factory = PenyediaViewModel.Factory),
    enabled: Boolean = true
){
    var hwnUI = InsertKdgViewModel.kdgUiState.hewanOption

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        DropDownHwn(
            title = "Pilih Hewan",
            options = hwnUI.map { it.nama_hewan },
            selectedOption = insertKdgUiEvent.nama_hewan,
            onOptionSelected = { nama_hewan ->
                onValueChange(insertKdgUiEvent.copy(nama_hewan = nama_hewan))

            }
        )

        OutlinedTextField(
            value = insertKdgUiEvent.kapasitas,
            onValueChange = {onValueChange(insertKdgUiEvent.copy(kapasitas = it))},
            label = { Text("Kapasitas Kandang") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        OutlinedTextField(
            value = insertKdgUiEvent.lokasi,
            onValueChange = {onValueChange(insertKdgUiEvent.copy(lokasi = it))},
            label = { Text("Masukkan Lokasi") },
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
fun DropDownHwn(
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