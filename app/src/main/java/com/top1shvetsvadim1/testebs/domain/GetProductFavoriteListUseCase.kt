package com.top1shvetsvadim1.testebs.domain

class GetProductFavoriteListUseCase(
    private val repository: ProductRepository
) {
    operator fun invoke() = repository.getProductFavoriteList()
}