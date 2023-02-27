package com.example.tepresto.ui.ocupacion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.*
import com.example.tepresto.data.local.entity.OcupacionEntity
import com.example.tepresto.data.remote.TodoApi
import com.example.tepresto.data.remote.dto.ArticuloDto
import com.example.tepresto.data.repository.OcupacionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update

data class OcupacionUiState(
    val ocupacionesList: List<OcupacionEntity> = emptyList(),
    val articulosList: List<ArticuloDto> = emptyList()
)

@HiltViewModel
class OcupacionViewModel @Inject constructor(
    private val ocupacionRepository: OcupacionRepository,
    private val todoApi: TodoApi
) : ViewModel() {

    var descripcion by mutableStateOf("")
    var sueldo by mutableStateOf("")

    var uiState = MutableStateFlow(OcupacionUiState())
        private set

    init {
        getLista()
    }

    fun getLista() {
        viewModelScope.launch(Dispatchers.IO) {
            //refrescarOcupaciones()
            getArticulosFromApi()
        }
    }

    private  suspend fun getArticulosFromApi(){
        val articulos = todoApi.getList()
        uiState.update {
            it.copy(articulosList = articulos)
        }
    }

    private suspend fun refrescarOcupaciones() {
        ocupacionRepository.getList().collect { lista ->
            uiState.update {
                it.copy(ocupacionesList = lista)
            }
        }
    }

    fun insertar() {
        val ocupacion = OcupacionEntity(
            descripcion = descripcion,
            sueldo = sueldo.toDoubleOrNull() ?: 0.0
        )

        viewModelScope.launch(Dispatchers.IO) {
            ocupacionRepository.insert(ocupacion)
            Limpiar()
        }
    }

    private fun Limpiar() {
        descripcion = ""
        sueldo = ""
    }

}
