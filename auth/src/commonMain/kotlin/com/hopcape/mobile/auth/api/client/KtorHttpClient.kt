package com.hopcape.mobile.auth.api.client

import com.hopcape.mobile.auth.data.remote.dto.ApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

class KtorHttpClient(
    private val httpClient: HttpClient
): ApiClient {

    override suspend fun <T : Any> post(
        endPoint: EndPoint,
        body: Any,
        responseType: KClass<T>
    ): Result<ApiResponse<T>> {
        val response = httpClient.get(endPoint.value) {
            contentType(Json)
           // setBody(body)
        }

        return try {
            Result.success(ApiResponse(
                code = response.status.value,
                message = response.status.description,
                data = response.decodeJsonTo(responseType)
            ))
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    @OptIn(InternalSerializationApi::class)
    private suspend fun <T : Any> HttpResponse.decodeJsonTo(responseType: KClass<T>): T {
        val json = Json { ignoreUnknownKeys = true }
        return json.decodeFromString(responseType.serializer(), this.bodyAsText())
    }
}