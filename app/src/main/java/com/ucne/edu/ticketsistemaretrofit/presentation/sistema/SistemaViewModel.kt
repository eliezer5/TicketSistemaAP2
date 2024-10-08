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
            when(result) {
                is Resource.Error -> _uiState.update { it.copy(error = "no obtiene") }
                is Resource.Loading -> TODO()
                is Resource.Success -> _uiState.update {
                    it.copy(
                        sistemas = result.data?: emptyList()
                    )
                }
            }
        }
    }


    private fun save(){
        viewModelScope.launch {
            val result = sistemaRepository.addSistema(uiState.value.toEntity())
            when(result) {
                is Resource.Error -> _uiState.update { it.copy(error = "no agrega") }
                is Resource.Loading -> TODO()
                is Resource.Success -> _uiState.update {
                    it.copy(
                        mensaje = "GuardadoCorrectamente"
                    )
                }
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

    fun onEvent(event: SistemaEvent){
        when(event){
            SistemaEvent.Save -> save()
            is SistemaEvent.onChangeDescription -> onChangeDescription(event.description)
            is SistemaEvent.onChangeNombre -> onChangeNombre(event.nombre)
        }
    }


}