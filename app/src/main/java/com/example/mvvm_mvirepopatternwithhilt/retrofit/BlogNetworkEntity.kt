package com.example.mvvm_mvirepopatternwithhilt.retrofit

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class BlogNetworkEntity(

    @SerializedName("pk")
    @Expose
    val id: Int,

    @SerializedName("body")
    @Expose
    val body: String,

    @SerializedName("category")
    @Expose
    val category: String,

    @SerializedName("image")
    @Expose
    val image: String,

    @SerializedName("title")
    @Expose
    val title: String
)
