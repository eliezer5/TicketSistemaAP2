package com.ucne.edu.ticketsistemaretrofit.presentation.navigation

import kotlinx.serialization.Serializable


sealed  class Screen {

    @Serializable
    data object SistemaList: Screen()
    @Serializable
    data class Sistema(val sistemaId: Int): Screen()

    @Serializable
    data object TicketList: Screen()
    @Serializable
    data class Ticket(val ticketId: Int): Screen()
}