package com.top1shvetsvadim1.testebs.domain

class InsertItemToFavouriteUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productItem: ProductItem) = repository.insertItemToFavorite(productItem)
}