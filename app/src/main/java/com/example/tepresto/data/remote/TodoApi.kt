package com.example.tepresto.data.remote

import com.example.tepresto.data.remote.dto.ArticuloDto
import retrofit2.http.GET

interface TodoApi {
    @GET("/api/articulos")
    suspend fun getList(): List<ArticuloDto>
}