package org.itb.sga.features.student.alu_materias

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.itb.sga.core.createHttpClient
import org.itb.sga.data.network.AluMateria
import org.itb.sga.data.network.AluMateriasResult
import org.itb.sga.features.common.home.HomeViewModel
import org.itb.sga.data.network.Error

class AluMateriasViewModel: ViewModel() {
    val client = createHttpClient()
    val service = AluMateriasService(client)

    private val _data = MutableStateFlow<List<AluMateria>>(emptyList())
    val data: StateFlow<List<AluMateria>> = _data

    fun onloadAluMaterias(id: Int, homeViewModel: HomeViewModel) {
        homeViewModel.changeLoading(true)
        viewModelScope.launch {
            try {
                val result = service.fetchAluMaterias(id)
                when (result) {
                    is AluMateriasResult.Success -> {
                        _data.value = result.aluMateria
                        homeViewModel.clearError()
                    }
                    is AluMateriasResult.Failure -> {
                        homeViewModel.addError(result.error)
                    }
                }
            } catch (e: Exception) {
                homeViewModel.addError(Error(title = "Error", error = "${e.message}"))
            } finally {
                homeViewModel.changeLoading(false)
            }
        }
    }
}