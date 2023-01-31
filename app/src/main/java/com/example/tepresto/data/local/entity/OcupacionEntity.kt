package com.example.tepresto.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ocupaciones")
data class OcupacionEntity(
    @PrimaryKey(autoGenerate = true)
    val ocupacionId: Int?=null,
    val descripcion: String,
    val sueldo: Double
)