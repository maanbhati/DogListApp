package com.todo.networkmodule.dto

import com.google.gson.annotations.SerializedName

data class DogImageResponse(
    @SerializedName("message")
    val message: String
)
