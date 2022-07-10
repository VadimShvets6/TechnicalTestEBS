package com.top1shvetsvadim1.testebs.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.top1shvetsvadim1.testebs.data.network.ApiService
import com.top1shvetsvadim1.testebs.domain.ProductItem

class ProductPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, ProductItem>() {

    override fun getRefreshKey(state: PagingState<Int, ProductItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductItem> {

        return try {
            val currentPage = params.key ?:1
            val response = apiService.getListOfMatch(currentPage)
            val data = response.body()?.listProductItem?: emptyList()
            val responseData = mutableListOf<ProductItem>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if(currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e : Exception){
            LoadResult.Error(e)
        }
    }
}