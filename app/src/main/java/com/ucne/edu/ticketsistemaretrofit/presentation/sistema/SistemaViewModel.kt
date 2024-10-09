package com.ucne.edu.ticketsistemaretrofit.presentation.sistema

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.edu.ticketsistemaretrofit.repository.SistemaRepository
import com.ucne.edu.ticketsistemaretrofit.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SistemaViewModel @Inject constructor(
    private val sistemaRepository: SistemaRepository
): ViewModel(){
    private val _uiState = MutableStateFlow(Uistate())
    val uiState = _uiState.asStateFlow()


    init {
        getSistemas()
    }

    private fun getSistemas(){
        viewModelScope.launch {
            val result = sistemaRepository.getSistemas()

            _uiState.update {
                it.copy(
                    sistemas = result.data?: emptyList()
                )
            }
        }
    }

    private fun save(){
        viewModelScope.launch {
            if (validar()){
                sistemaRepository.addSistema(uiState.value.toEntity())
                nuevo()
            }
        }
    }
    private fun onChangeNombre(nombre: String) {

        _uiState.update {
            it.copy(
                nombreSistema = nombre
            )
        }
    }

    private fun onChangeDescription(description: String) {

        _uiState.update {
            it.copy(
                descripcionSistema = description
            )
        }
    }

    private fun nuevo() {
        _uiState.update {
            it.copy(
                sistemaId = null,
                nombreSistema = "",
                descripcionSistema = "",
                errorNombre = "",
                errorDescription = ""
            )
        }
    }

    private fun validar(): Boolean{

        var error = false

        _uiState.update {
            it.copy(
               errorNombre = if (uiState.value.nombreSistema.isBlank()){
                   error = true
                   "El nombre no puede estar vacio"
               }else "",

                errorDescription = if (uiState.value.descripcionSistema.isBlank()){
                    error = true
                    "La descripcion no puede estar vacia"
                }else ""
            )
        }
        return !error
    }

    fun getDescripcionById(prioridadId: Int): String {

        val descripcion =
            uiState.value.sistemas.firstOrNull { it.sistemaId == prioridadId }?.nombreSistema
                ?: ""
        return descripcion
    }

    fun onEvent(event: SistemaEvent){
        when(event){
            SistemaEvent.Save -> save()
            is SistemaEvent.onChangeDescription -> onChangeDescription(event.description)
            is SistemaEvent.onChangeNombre -> onChangeNombre(event.nombre)
        }
    }


}