package com.ucne.edu.ticketsistemaretrofit.data.remote

import com.ucne.edu.ticketsistemaretrofit.data.remote.dto.SistemasDto
import com.ucne.edu.ticketsistemaretrofit.data.remote.dto.TicketDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SistemaAPI {

    @GET("api/Sistemas")
    suspend fun getSistemas(): List<SistemasDto>

    @POST("api/Sistemas/")
    suspend fun addSistema(@Body sistemasDto: SistemasDto): SistemasDto

    @GET("api/Tickets")
    suspend fun getTickets(): List<TicketDto>

    @POST("api/Tickets/")
    suspend fun addTicket(@Body ticketDto: TicketDto): TicketDto

}