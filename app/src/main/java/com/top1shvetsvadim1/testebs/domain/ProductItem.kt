package com.top1shvetsvadim1.testebs.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductItem(
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("details")
    @Expose
    val details: String,
    @SerializedName("size")
    @Expose
    val size: String,
    @SerializedName("colour")
    @Expose
    val colour: String,
    @SerializedName("price")
    @Expose
    val price: Int,
    @SerializedName("main_image")
    @Expose
    val mainImage: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    var isFavorite : Boolean = false
)
