package com.top1shvetsvadim1.testebs.data.network

import com.top1shvetsvadim1.testebs.domain.ProductItem
import com.top1shvetsvadim1.testebs.domain.ProductList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("products")
    suspend fun getListOfMatch(
        @Query(QUERY_PARAM_PAGE) page : Int,
        @Query(QUERY_PARAM_PAGE_SIZE) page_size : Int = PARAM_PAGE_SIZE
    ) : Response<ProductList>

    @GET("products/{id}")
    suspend fun getProductItemById(
        @Path(QUERY_PARAM_ID) id : Int
    ) : ProductItem

    companion object{
        private const val PARAM_PAGE_SIZE = 10
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_PAGE_SIZE = "page_size"
        private const val QUERY_PARAM_ID = "id"
    }

}