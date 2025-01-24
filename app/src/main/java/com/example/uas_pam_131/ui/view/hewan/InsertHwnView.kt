package com.example.uas_pam_131.ui.view.hewan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_pam_131.ui.customwidget.CostumeTopAppBar
import com.example.uas_pam_131.ui.navigation.DestinasiInsertHwn
import com.example.uas_pam_131.ui.navigation.DestinasiInsertPtgs
import com.example.uas_pam_131.ui.viewmodel.PenyediaViewModel
import com.example.uas_pam_131.ui.viewmodel.hewan.InsertHwnUiEvent
import com.example.uas_pam_131.ui.viewmodel.hewan.InsertHwnUiState
import com.example.uas_pam_131.ui.viewmodel.hewan.InsertHwnViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryHwnScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertHwnViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiInsertHwn.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) {innerPadding ->
        EntryBodyHwn(
            insertHwnUiState = viewModel.hwnUiState,
            onHewanValueChange = viewModel::updateInsertHwnState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertHwn()
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
fun EntryBodyHwn(
    insertHwnUiState: InsertHwnUiState,
    onHewanValueChange: (InsertHwnUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputHwn(
            insertHwnUiEvent = insertHwnUiState.insertHwnUiEvent,
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
fun FormInputHwn(
    insertHwnUiEvent: InsertHwnUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertHwnUiEvent) -> Unit = {},
    enabled: Boolean = true
){
    val tipe_pakan = listOf("Herbivora", "Karnivora", "Omnivora")


    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        OutlinedTextField(
            value = insertHwnUiEvent.nama_hewan,
            onValueChange = {onValueChange(insertHwnUiEvent.copy(nama_hewan = it))},
            label = { Text("Nama Hewan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertHwnUiEvent.populasi,
            onValueChange = {onValueChange(insertHwnUiEvent.copy(populasi = it))},
            label = { Text("Populasi Hewan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        OutlinedTextField(
            value = insertHwnUiEvent.zona_wilayah,
            onValueChange = {onValueChange(insertHwnUiEvent.copy(zona_wilayah = it))},
            label = { Text("Zona Wilayah Hewan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            tipe_pakan.forEach { tp ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = insertHwnUiEvent.tipe_pakan == tp,
                        onClick = {
                            onValueChange(insertHwnUiEvent.copy(tipe_pakan = tp))
                        },

                        )
                    Text(
                        text = tp,
                    )
                }
            }
        }

        if (enabled){
            Text(
                text = "Isi Semua Data!",
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}