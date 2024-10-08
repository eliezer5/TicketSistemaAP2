package com.ucne.edu.ticketsistemaretrofit.repository

import com.ucne.edu.ticketsistemaretrofit.data.remote.SistemaAPI
import com.ucne.edu.ticketsistemaretrofit.data.remote.dto.SistemasDto
import com.ucne.edu.ticketsistemaretrofit.utils.Resource
import javax.inject.Inject

class SistemaRepository @Inject constructor(
    private val sistemaAPI: SistemaAPI
) {
    suspend fun getSistemas() : Resource<List<SistemasDto>>{
        val result = try {
            sistemaAPI.getSistemas()
        } catch (e : Exception){
            return Resource.Error("error al obtener x sistema")
        }
        return Resource.Success(result)
    }

    suspend fun addSistema(sistemasDto: SistemasDto) : Resource<SistemasDto>{
        val result = try {
            sistemaAPI.addSistema(sistemasDto)
        } catch (e : Exception){
            return Resource.Error("error al crear")
        }
        return Resource.Success(result)
    }
}