package com.ucne.edu.ticketsistemaretrofit.presentation.ticket

sealed interface TicketEvent {

    object Save : TicketEvent
    data class OnChangeDate(val date: String) : TicketEvent
    data class OnChangeAsunto(val asunto: String) : TicketEvent
    data class OnChangeDescripcion(val descripcion: String) : TicketEvent
    data class OnChangeSistemaId(val sistemaId: Int) : TicketEvent
    data class OnChangeSolicitadoPor(val solicitadoPor: String) : TicketEvent


}