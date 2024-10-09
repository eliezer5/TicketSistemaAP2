package com.ucne.edu.ticketsistemaretrofit.repository

import com.ucne.edu.ticketsistemaretrofit.data.remote.SistemaAPI
import com.ucne.edu.ticketsistemaretrofit.data.remote.dto.SistemasDto
import com.ucne.edu.ticketsistemaretrofit.data.remote.dto.TicketDto
import com.ucne.edu.ticketsistemaretrofit.utils.Resource
import javax.inject.Inject

class TicketRepository @Inject constructor(
    private val sistemaAPI: SistemaAPI
) {
    suspend fun getTickets() : Resource<List<TicketDto>> {
        val result = try {
            sistemaAPI.getTickets()
        } catch (e : Exception){
            return Resource.Error("error al obtener x ticket")
        }
        return Resource.Success(result)
    }

    suspend fun addTicket(ticketDto: TicketDto) : Resource<TicketDto> {
        val result = try {
            sistemaAPI.addTicket(ticketDto)
        } catch (e : Exception){
            return Resource.Error("error al crear")
        }
        return Resource.Success(result)
    }
}