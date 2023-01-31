package com.example.tepresto.ui.ocupacion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OcupacionScreen( viewModel: OcupacionViewModel = hiltViewModel()) {
    var descripcion by remember { mutableStateOf("") }
    var sueldo by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = sueldo,
            onValueChange = { sueldo = it },
            label = { Text("Salario") }
        )

        ExtendedFloatingActionButton(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            text= { Text("Guardar") },
            icon = { Icon(imageVector = Icons.Filled.Save, contentDescription = "Save") },
            onClick = { viewModel.insertar(descripcion, sueldo) }
        )
    }
}