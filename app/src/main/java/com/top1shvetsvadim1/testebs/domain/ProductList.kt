package com.top1shvetsvadim1.testebs.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("results")
    @Expose
    val listProductItem : List<ProductItem>
)