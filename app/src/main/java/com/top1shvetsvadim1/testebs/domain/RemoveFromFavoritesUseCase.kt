package com.top1shvetsvadim1.testebs.domain

class RemoveFromFavoritesUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productItemId : Int) = repository.removeFromFavorite(productItemId)
}