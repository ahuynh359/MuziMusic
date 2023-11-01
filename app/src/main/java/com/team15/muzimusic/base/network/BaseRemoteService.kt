package com.team15.muzimusic.base.network

import android.util.Log
import retrofit2.Response

open class BaseRemoteService {

    protected suspend fun <T : Any> callApi(call: suspend () -> Response<T>): NetworkResult<T> {
        val response: Response<T>
        try {
            response = call.invoke()
        } catch (t: Throwable) {
            t.printStackTrace()
            return NetworkResult.Error(ResponseError("Có lỗi xảy ra", 500))
        }

        return if (response.isSuccessful) {
            if (response.body() == null)
                NetworkResult.Error(
                    ResponseError("Response without body", 200)
                )
            else NetworkResult.Success(response.body()!!)
        } else {
            if(response.code() >= 500){
                NetworkResult.Error(ResponseError("Server bị lỗi! Xin hãy thử lại sau", response.code()))
            }else{
                val errorBody = response.errorBody()?.string() ?: ""
                Log.d("vinh", "error body: $errorBody")
                NetworkResult.Error(ResponseError.fromJson(errorBody, response.code()))
            }
        }
    }
}