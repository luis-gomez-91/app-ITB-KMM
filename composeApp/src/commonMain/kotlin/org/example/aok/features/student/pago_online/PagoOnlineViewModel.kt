package org.example.aok.features.student.pago_online

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.aok.core.createHttpClient
import org.example.aok.core.logInfo
import org.example.aok.data.network.DatosFacturacion
import org.example.aok.data.network.PagoOnline
import org.example.aok.data.network.PagoOnlineForm
import org.example.aok.data.network.PagoOnlineResult

class PagoOnlineViewModel : ViewModel() {
    val client = createHttpClient()
    val service = PagoOnlineService(client)

    private val _data = MutableStateFlow<PagoOnline?>(null)
    val data: StateFlow<PagoOnline?> = _data

    private val _error = MutableStateFlow<String>("")
    val error: StateFlow<String> = _error

    private val _searchQuery = MutableStateFlow<String>("")
    val searchQuery: StateFlow<String> get() = _searchQuery

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _selectedRubros = MutableStateFlow<List<Double>>(emptyList())
    val selectedRubros: StateFlow<List<Double>> = _selectedRubros

    private val _total = MutableStateFlow<Double>(0.00)
    val total: StateFlow<Double> = _total

    private val _switchStates = MutableStateFlow<List<Boolean>>(emptyList())
    val switchStates: StateFlow<List<Boolean>> = _switchStates

    fun initializeSwitchStates(size: Int) {
        _switchStates.value = List(size) { index -> index == 0 }
    }

    fun updateSwitchState(index: Int, enabled: Boolean) {
        val updatedStates = _switchStates.value.toMutableList()
        updatedStates[index] = enabled
        _switchStates.value = updatedStates
    }

    fun addRubro(rubroValor: Double) {
        _selectedRubros.value = _selectedRubros.value + rubroValor
        logInfo("pago_online", "${_selectedRubros.value}")
        _total.value = calculateTotal()
    }

    fun removeRubro(index: Int) {
        logInfo("pago_online", "${index}")
        if (index in _selectedRubros.value.indices) {
            val mutableList = _selectedRubros.value.toMutableList()
            mutableList.removeAt(index)
            _selectedRubros.value = mutableList
            _total.value = calculateTotal()
        } else {
            println("Posición no válida: $index")
        }
        logInfo("pago_online", "${_selectedRubros.value}")
    }

    fun calculateTotal(): Double {
        return _selectedRubros.value.sum()
    }



    fun onloadPagoOnline(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = service.fetchPagoOnline(id)
                logInfo("pago_online", "$result")

                when (result) {
                    is PagoOnlineResult.Success -> {
                        _data.value = result.pagoOnline
                        _error.value = ""
                    }
                    is PagoOnlineResult.Failure -> {
                        _error.value = result.error.error
                    }
                }

            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun sendPagoOnline(idInscripcion: Int, valor: Double, datosFacturacion: DatosFacturacion) {
        viewModelScope.launch {
            _isLoading.value = true
            val form = PagoOnlineForm(
                inscripcion = idInscripcion,
                valor = valor,
                correo = datosFacturacion.correo,
                nombre = datosFacturacion.nombre,
                ruc = datosFacturacion.cedula,
                telefono = datosFacturacion.telefono
            )
            try {
                val result = service.sendPagoOnline(form)
//                logInfo("pago_online", "$result")



            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }


}


