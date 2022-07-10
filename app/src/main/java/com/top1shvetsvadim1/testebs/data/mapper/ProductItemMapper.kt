package com.top1shvetsvadim1.testebs.data.mapper

import com.top1shvetsvadim1.testebs.data.database.ProductItemDbModel
import com.top1shvetsvadim1.testebs.domain.ProductItem

class ProductItemMapper {

    fun mapEntityToDbModel(productItem: ProductItem) = ProductItemDbModel(
        id = productItem.id,
        name = productItem.name,
        details = productItem.details,
        size = productItem.size,
        mainImage = productItem.mainImage,
        colour = productItem.colour,
        price = productItem.price,
        isFavorite = productItem.isFavorite
    )

    fun mapDbModelToEntity(productItemDbModel: ProductItemDbModel) = ProductItem(
        id = productItemDbModel.id,
        name = productItemDbModel.name,
        details = productItemDbModel.details,
        size = productItemDbModel.size,
        mainImage = productItemDbModel.mainImage,
        colour = productItemDbModel.colour,
        price = productItemDbModel.price,
        isFavorite = productItemDbModel.isFavorite
    )

    fun mapListDbModelToListEntity(list : List<ProductItemDbModel>) = list.map{
        mapDbModelToEntity(it)
    }

}