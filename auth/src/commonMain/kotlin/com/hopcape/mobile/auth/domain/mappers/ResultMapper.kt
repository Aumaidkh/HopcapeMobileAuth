package com.hopcape.mobile.auth.domain.mappers

fun <I,O> Result<I>.mapResult(mapper: (I) -> O): Result<O> {
    return when {
        isSuccess -> Result.success(mapper(getOrThrow()))
        isFailure -> Result.failure(exceptionOrNull() ?: Exception("Unknown error"))
        else -> Result.failure(Exception("Unknown Exception"))
    }
}