package org.example.aok.features.teacher.pro_cronograma

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.aok.core.createHttpClient
import org.example.aok.core.logInfo
import org.example.aok.data.network.Error
import org.example.aok.data.network.ProCronogramaResult
import org.example.aok.data.network.pro_cronograma.ProCronograma
import org.example.aok.features.common.home.HomeViewModel

class ProCronogramaViewModel : ViewModel() {
    val client = createHttpClient()
    val service = ProCronogramaService(client)

    private val _data = MutableStateFlow<List<ProCronograma>>(emptyList())
    val data: StateFlow<List<ProCronograma>> = _data

    private val _error = MutableStateFlow<Error?>(null)
    val error: StateFlow<Error?> = _error

    fun clearError() {
        _error.value = null
    }

    fun addError(newValue: Error) {
        _error.value = newValue
    }

    fun onloadProHorarios(
        id: Int,
        homeViewModel: HomeViewModel
    ) {
        viewModelScope.launch {
            homeViewModel.changeLoading(true)
            try {
                val result = service.fetchProCronograma(id)
                logInfo("pro_cronograma", "$result")

                when (result) {
                    is ProCronogramaResult.Success -> {
                        _data.value = result.data
                        clearError()
                    }
                    is ProCronogramaResult.Failure -> {
                        homeViewModel.addError(result.error)
                    }
                }

            } catch (e: Exception) {
                addError(Error(title = "Error", error = "${e.message}"))
            } finally {
                homeViewModel.changeLoading(false)
            }
        }
    }




}


