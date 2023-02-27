package com.example.tepresto.ui.ocupacion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tepresto.data.local.entity.OcupacionEntity
import com.example.tepresto.data.remote.dto.ArticuloDto


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OcupacionScreen(viewModel: OcupacionViewModel = hiltViewModel()) {

    Column(Modifier.fillMaxSize()) {
        OcupacionBody(viewModel)

        val uiState by viewModel.uiState.collectAsState()
        //OcupacionListScreen(uiState.ocupacionesList)

        ArticuloListScreen(uiState.articulosList)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun OcupacionBody(
    viewModel: OcupacionViewModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.descripcion,
            onValueChange = { viewModel.descripcion = it },
            label = { Text("Descripción") }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.sueldo,
            onValueChange = { viewModel.sueldo = it },
            label = { Text("Salario") }
        )

        ExtendedFloatingActionButton(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            text = { Text("Guardar") },
            icon = { Icon(imageVector = Icons.Filled.Save, contentDescription = "Save") },
            onClick = { viewModel.insertar() }
        )
    }
}

@Composable
private fun OcupacionListScreen(ocupacionList: List<OcupacionEntity>) {
    LazyColumn {
        items(ocupacionList) { ocupacion ->
            OcupacionRow(ocupacion)
        }
    }
}

@Composable
fun ArticuloListScreen(articulos: List<ArticuloDto>) {
    LazyColumn() {
        items(articulos) { art ->
            ArticuloRow(art)
        }
    }
}

@Composable
fun ArticuloRow(articuloDto: ArticuloDto) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = articuloDto.descripcion, style = MaterialTheme.typography.titleLarge)
        Text(text = articuloDto.precio.toString())
    }
}

@Preview(showSystemUi = true)
@Composable
fun ArticuloListScreenPreview() {
    var articulos = listOf(
        ArticuloDto(1, "Disco ssd", "", 100.0, 1),
        ArticuloDto(1, "Camara", "", 200.0, 1)
    )
    ArticuloListScreen(articulos)
}

@Composable
private fun OcupacionRow(ocupacion: OcupacionEntity) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = ocupacion.descripcion,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(3f)
            )
            Text(
                String.format("%.2f", ocupacion.sueldo),
                textAlign = TextAlign.End,
                modifier = Modifier.weight(2f)
            )
        }
        Divider(Modifier.fillMaxWidth())
    }
}
