package com.top1shvetsvadim1.testebs.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_items")
data class ProductItemDbModel(
    @PrimaryKey
    val id : Int,
    val name: String,
    val details: String,
    val size: String,
    val colour: String,
    val price: Int,
    val mainImage: String,
    var isFavorite: Boolean = false
) {
}