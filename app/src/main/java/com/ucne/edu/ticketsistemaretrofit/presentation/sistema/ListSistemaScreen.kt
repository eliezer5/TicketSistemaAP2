package com.ucne.edu.ticketsistemaretrofit.presentation.sistema

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ucne.edu.ticketsistemaretrofit.data.remote.dto.SistemasDto

@Composable
fun ListSistemaScreen(
    goToAdd: () -> Unit,
    onSelect: (Int) -> Unit,
    viewModel: SistemaViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ListSistemaBodyScreen(uiState, goToAdd, onSelect)
}

@Composable
fun ListSistemaBodyScreen(
    uiState: Uistate,
    goToAdd: () -> Unit,
    onSelect: (Int) -> Unit,
) {
    Box(
    modifier = Modifier.fillMaxSize() // Contenedor principal
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Lista Sistemas", modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {
            Text(
                text = "Nombre Sistema",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Descripción",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(uiState.sistemas) {
                SistemaRow(it, onSelect)
            }
        }
    }

    FloatingActionButton(
        onClick = goToAdd,
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(top = 10.dp, end = 20.dp, bottom = 35.dp)
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Agregar Venta")
    }
}
}


@Composable
fun SistemaRow(it: SistemasDto, onSelect: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onSelect(it.sistemaId ?: 0) }
            .fillMaxSize()
    ) {


        Text(
            text = it.nombreSistema ?: "",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = it.descripcionSistema.toString(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewListSistemaScreen() {
    ListSistemaBodyScreen(Uistate(),goToAdd = {}, onSelect = {})
}
