package com.ucne.edu.ticketsistemaretrofit.presentation.sistema

import com.ucne.edu.ticketsistemaretrofit.data.remote.dto.SistemasDto

data class Uistate(
    val sistemaId: Int? = null,
    val nombreSistema: String = "",
    val descripcionSistema: String = "",
    val sistemas: List<SistemasDto> = emptyList(),
    val errorNombre: String = "",
    val errorDescription: String = "",
    val mensaje: String = ""

)

fun Uistate.toEntity() = SistemasDto(
    sistemaId = sistemaId,
    nombreSistema = nombreSistema,
    descripcionSistema = descripcionSistema
)