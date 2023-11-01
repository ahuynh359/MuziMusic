package com.team15.muzimusic.base.network

import com.beust.klaxon.Klaxon

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val body: T) : NetworkResult<T>()
    data class Error(val responseError: ResponseError) : NetworkResult<Nothing>()
}

data class ResponseError(val message: String? = null, val code: Int = -1) {
    companion object{
        fun fromJson(errorBody: String, code: Int): ResponseError {
            return try {
                val response = Klaxon().parse<ResponseMessage>(errorBody)
                ResponseError(response?.message, code)
            } catch (e: Exception) {
                ResponseError("Server Error", code)
            }
        }
    }
}

data class ResponseMessage(val message: String)