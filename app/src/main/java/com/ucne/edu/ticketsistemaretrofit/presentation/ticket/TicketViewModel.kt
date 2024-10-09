package com.ucne.edu.ticketsistemaretrofit.presentation.ticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.edu.ticketsistemaretrofit.data.remote.dto.TicketDto
import com.ucne.edu.ticketsistemaretrofit.repository.TicketRepository
import com.ucne.edu.ticketsistemaretrofit.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val ticketRepository: TicketRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(Uistate())
    val uiState = _uiState.asStateFlow()

    init {
        getTickets()
    }


    private fun getTickets() {
        viewModelScope.launch {
            val result = ticketRepository.getTickets()
            when (result) {
                is Resource.Error -> _uiState.update { it.copy(error = "no obtiene") }
                is Resource.Loading -> TODO()
                is Resource.Success -> _uiState.update {
                    it.copy(
                        tickets = result.data ?: emptyList()
                    )
                }
            }
        }
    }

    private fun save() {
        viewModelScope.launch {
            val result = ticketRepository.addTicket(uiState.value.toEntity())
            when (result) {
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

    private fun onChangeDate(date: String) {
        _uiState.update {
            it.copy(
                fecha = date
            )
        }
    }

    private fun onChangeAsunto(asunto: String) {
        _uiState.update {
            it.copy(
                asunto = asunto
            )
        }
    }

    private fun onChangeDescripcion(descripcion: String) {
        _uiState.update {
            it.copy(
                descripcion = descripcion
            )
        }
    }

    private fun onChangeSistemaId(sistemaId: Int) {
        _uiState.update {
            it.copy(
                sistemaId = sistemaId
            )
        }
    }

    private fun onChangeSolicitadoPor(solicitadoPor: String) {
        _uiState.update {
            it.copy(
                solicitadoPor = solicitadoPor
            )
        }
    }

    fun onEvent(event: TicketEvent) {
        when (event) {
            TicketEvent.Save -> save()
            is TicketEvent.OnChangeDate -> onChangeDate(event.date)
            is TicketEvent.OnChangeAsunto -> onChangeAsunto(event.asunto)
            is TicketEvent.OnChangeDescripcion -> onChangeDescripcion(event.descripcion)
            is TicketEvent.OnChangeSistemaId -> onChangeSistemaId(event.sistemaId)
            is TicketEvent.OnChangeSolicitadoPor -> onChangeSolicitadoPor(event.solicitadoPor)

        }
    }


}