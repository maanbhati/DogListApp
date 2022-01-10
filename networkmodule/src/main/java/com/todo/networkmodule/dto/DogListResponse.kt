package com.todo.networkmodule.dto


import com.google.gson.annotations.SerializedName

data class DogListResponse(
    @SerializedName("message")
    val message: Message
)