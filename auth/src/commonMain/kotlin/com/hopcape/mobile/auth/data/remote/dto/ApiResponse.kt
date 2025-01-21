package com.hopcape.mobile.auth.data.remote.dto

data class ApiResponse<T>(
    val code: Int,
    val data: T,
    val message: String
)