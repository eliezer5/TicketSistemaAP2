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

            _uiState.update {
                it.copy(
                    tickets = result.data ?: emptyList()
                )
            }
        }
    }

    private fun save() {
        viewModelScope.launch {
            if (validar()) {
                ticketRepository.addTicket(uiState.value.toEntity())
                nuevo()
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

    private fun validar(): Boolean {
        var error = false

        _uiState.update {
            it.copy(
                errorFecha = if (it.fecha.isBlank()) {
                    error = true
                    "La fecha no puede estar vacia"
                } else "",
                errorAsunto = if (it.asunto.isBlank()) {
                    error = true
                    "El asunto no puede estar vacio"
                } else "",
                errorDescripcion = if (it.descripcion.isBlank()) {
                    error = true
                    "La descripcion no puede estar vacia"
                } else "",
                errorSolicitadoPor = if (it.solicitadoPor.isBlank()) {
                    error = true
                    "Solicitado por no puede estar vacio"
                } else "",
                errorSistemaId = if (it.sistemaId == null) {
                    error = true
                    "El sistema no puede estar vacio"
                } else ""

            )
        }
        return !error

    }

    private fun nuevo() {
        _uiState.update {
            it.copy(
                ticketId = null,
                fecha = "",
                asunto = "",
                descripcion = "",
                sistemaId = null,
                solicitadoPor = "",
                errorFecha = "",
                errorAsunto = "",
                errorDescripcion = "",
                errorSolicitadoPor = "",
                errorSistemaId = ""
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