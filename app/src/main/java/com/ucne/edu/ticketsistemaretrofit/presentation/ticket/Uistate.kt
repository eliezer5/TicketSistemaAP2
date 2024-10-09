package com.ucne.edu.ticketsistemaretrofit.presentation.ticket

import com.ucne.edu.ticketsistemaretrofit.data.remote.dto.TicketDto

data class Uistate(
    val ticketId: Int? = null,
    val fecha: String = "",
    val asunto: String = "",
    val descripcion: String = "",
    val tickets: List<TicketDto> = emptyList(),
    val error: String = "",
    val mensaje: String = "",
    val sistemaId: Int? = null,
    val solicitadoPor: String = "",
    val errorFecha: String = "",
    val errorAsunto: String = "",
    val errorDescripcion: String = "",
    val errorSolicitadoPor: String = "",
    val errorSistemaId: String = ""

)

fun Uistate.toEntity() = TicketDto(
    ticketId = ticketId,
    fecha = fecha,
    asunto = asunto,
    descripcion = descripcion,
    sistemaId = sistemaId,
    solicitadoPor = solicitadoPor

)