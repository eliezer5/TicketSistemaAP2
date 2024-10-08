package com.ucne.edu.ticketsistemaretrofit.presentation.sistema

sealed interface SistemaEvent {
    data object Save: SistemaEvent
    data class onChangeNombre(val nombre: String): SistemaEvent
    data class onChangeDescription(val description: String): SistemaEvent
}