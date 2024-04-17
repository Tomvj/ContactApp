package com.example.contacttask.Retrofit

import com.example.contacttask.Responsemodel.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("api/")
    suspend fun getRandomUsers(
        @Query("results") results: Int,
        @Query("page") page: Int,
    @Query("inc") inc: String): ResponseModel
}
