package com.todo.networkmodule.api

import com.todo.networkmodule.dto.DogImageResponse
import com.todo.networkmodule.dto.DogListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("breeds/list/all")
    suspend fun getDogList(): Response<DogListResponse>

    @GET("breed/{breedName}/images/random")
    suspend fun getDogImage(@Path(value = "breedName") breedName: String):
            Response<DogImageResponse>

    companion object {
        const val BASE_URL = "https://dog.ceo/api/"
    }
}