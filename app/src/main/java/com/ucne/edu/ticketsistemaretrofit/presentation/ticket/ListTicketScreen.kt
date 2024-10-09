package com.ucne.edu.ticketsistemaretrofit.presentation.ticket

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ucne.edu.ticketsistemaretrofit.data.remote.dto.TicketDto
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ucne.edu.ticketsistemaretrofit.presentation.sistema.SistemaViewModel


@Composable
fun ListTicketScreen(
    goToAdd: () -> Unit,
    onSelect: (Int) -> Unit,
    viewModel: TicketViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ListTicketBodyScreen(uiState, goToAdd, onSelect)

}

@Composable
fun ListTicketBodyScreen(
    uiState: Uistate,
    goToAdd: () -> Unit,
    onSelect: (Int) -> Unit,
    viewModel: SistemaViewModel = hiltViewModel()
) {
    Box(
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize().padding(top = 16.dp)
        ) {
            Text(text = "Lista Tickets", modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(20.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
            ){
                Text(
                    text = "Asunto",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(0.8f)
                )
                Text(
                    text = "Descripción ",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1.2f)
                )

                Text(
                    text = "Fecha",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Sistema",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Solicitado Por",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.height(40.dp))
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(uiState.tickets) {
                    TicketRow(it, onSelect, viewModel)
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
fun TicketRow(it: TicketDto, onSelect: (Int) -> Unit, viewModel: SistemaViewModel) {
    Row(
        modifier = Modifier
            .clickable { onSelect(it.ticketId ?: 0) }
            .fillMaxSize()
    ) {
        Text(
            text = it.asunto ?: "",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(0.8f)
        )
        Text(
            text = it.descripcion.toString(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1.2f)
        )
        Text(
            text = it.fecha.toString(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = viewModel.getDescripcionById(it.sistemaId?:0),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = it.solicitadoPor.toString(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )

    }
}
