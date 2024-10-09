package com.ucne.edu.ticketsistemaretrofit.data.remote.dto

data class TicketDto (
    val ticketId: Int?,
    val fecha: String? = null,
    val asunto: String? = null,
    val descripcion: String? = null,
    val sistemaId: Int? = null,
    val solicitadoPor : String? = null
)