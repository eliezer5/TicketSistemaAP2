package com.ucne.edu.ticketsistemaretrofit.data.remote

import com.ucne.edu.ticketsistemaretrofit.data.remote.dto.SistemasDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SistemaAPI {

    @GET("api/Sistemas")
    suspend fun getSistemas(): List<SistemasDto>

    @POST("api/Sistemas/")
    suspend fun addSistema(@Body sistemasDto: SistemasDto): SistemasDto
}