package com.example.tepresto.ui.ocupacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.roomexample.data.local.entity.OcupacionEntity
import com.ucne.roomexample.data.repository.OcupacionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OcupacionViewModel @Inject constructor(
    private val ocupacionRepository: OcupacionRepository
) : ViewModel() {

    fun insertar(descripcion: String, sueldo: String ){

        val ocupacion = OcupacionEntity(
            descripcion = descripcion,
            sueldo = sueldo.toDoubleOrNull()?:0.0
        )

        viewModelScope.launch {
            ocupacionRepository.insert(ocupacion)
        }
    }
}