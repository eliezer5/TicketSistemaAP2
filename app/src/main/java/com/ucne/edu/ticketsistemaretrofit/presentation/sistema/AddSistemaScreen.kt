package com.ucne.edu.ticketsistemaretrofit.presentation.sistema

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AddSistemaScreen(
    goToBack: () -> Unit,
    viewModel: SistemaViewModel = hiltViewModel(),

    ) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    AddSistemaBodyScreen(uiState, goToBack, { event -> viewModel.onEvent(event) })

}

@Composable
fun AddSistemaBodyScreen(
    uiState: Uistate,
    goToBack: () -> Unit,
    onEvent: (SistemaEvent) -> Unit
) {
    Box(
    ) {
        Column(
            modifier = Modifier

                .fillMaxSize()
        ) {
            Text(text = "Agregar Sistema", modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                label = { Text(text = "Nombre") },
                value = uiState.nombreSistema ?: "",
                onValueChange = { onEvent(SistemaEvent.onChangeNombre(it)) },
                modifier = Modifier.fillMaxWidth()
            )
            uiState.errorNombre.let { error ->
                Text(text = error, color = Color.Red)
            }

            OutlinedTextField(
                label = { Text(text = "Descripción Sistema") },
                value = uiState.descripcionSistema,
                onValueChange = { onEvent(SistemaEvent.onChangeDescription(it)) },
                modifier = Modifier.fillMaxWidth()
            )
            uiState.errorDescription.let { error ->
                Text(text = error, color = Color.Red)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                OutlinedButton(onClick = {
                    onEvent(SistemaEvent.Save)
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Guardar")
                    Text(text = "Guardar")
                }
            }


        }
        FloatingActionButton(
            onClick = goToBack, modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(top = 10.dp, end = 20.dp, bottom = 35.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver"
            )
        }

    }

}

