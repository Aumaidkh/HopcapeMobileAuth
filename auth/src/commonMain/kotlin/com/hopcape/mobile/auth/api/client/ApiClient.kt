package com.hopcape.mobile.auth.api.client

import com.hopcape.mobile.auth.data.remote.dto.ApiResponse
import kotlin.reflect.KClass

interface ApiClient {

    suspend fun <T: Any> post(endPoint: EndPoint, body: Any, responseType: KClass<T>): Result<ApiResponse<T>>
}