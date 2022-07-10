package com.top1shvetsvadim1.testebs.domain

class GetProductItemByIdUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id : Int) = repository.loadProductItemById(id)
}