package com.example.tepresto.util

sealed class Resource <T>(val data: T? = null, val message: String? = null){
}