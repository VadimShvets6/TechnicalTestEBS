package com.top1shvetsvadim1.testebs.presentation.adapters.mainScreenAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import com.squareup.picasso.Picasso
import com.top1shvetsvadim1.testebs.R
import com.top1shvetsvadim1.testebs.databinding.ItemProductBinding
import com.top1shvetsvadim1.testebs.databinding.ItemProductFavoriteBinding
import com.top1shvetsvadim1.testebs.domain.ProductItem
import com.top1shvetsvadim1.testebs.presentation.adapters.favoriteScreenAdpater.FavoriteAdapter
import com.top1shvetsvadim1.testebs.presentation.adapters.favoriteScreenAdpater.viewHolders.ItemViewHolder
import com.top1shvetsvadim1.testebs.presentation.adapters.mainScreenAdapter.callbacks.DiffUtilsCallback
import java.util.*

class ProductAdapter : PagingDataAdapter<ProductItem, ItemViewHolder>(DiffUtilsCallback) {

    var onProductItemClickListeners: ((ProductItem) -> Unit)? = null
    var onAddToFavouriteClickListeners : ((ProductItem) ->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout = when (viewType) {
            FavoriteAdapter.VIEW_TYPE_FAVORITE -> R.layout.item_product_favorite
            FavoriteAdapter.VIEW_TYPE_NOT_FAVORITE -> R.layout.item_product
            else -> throw RuntimeException("Unknow viewType: $viewType")
        }

        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val productItem = getItem(position)
        val binding = holder.binding
        binding.root.setOnClickListener {
            if (productItem != null) {
                onProductItemClickListeners?.invoke(productItem)
            }
        }
        when(binding) {
            is ItemProductFavoriteBinding -> {
                with(binding) {
                    tvProductName.text = productItem?.name.toString()
                    tvSize.text = productItem?.size.toString()
                    tvPrice.text =
                        String.format(Locale.getDefault(), "$ %s,-", productItem?.price.toString())
                    tvSmallPrice.text =
                        String.format(Locale.getDefault(), "$ %s,-", productItem?.price.toString())
                    Picasso.get().load(productItem?.mainImage).into(ivProduct)
                    buttonHeart.setOnClickListener {
                        if (productItem != null) {
                            onAddToFavouriteClickListeners?.invoke(productItem)
                        }
                    }
                }
            }
            is ItemProductBinding -> {
                with(binding) {
                    tvProductName.text = productItem?.name.toString()
                    tvSize.text = productItem?.size.toString()
                    tvPrice.text =
                        String.format(Locale.getDefault(), "$ %s,-", productItem?.price.toString())
                    tvSmallPrice.text =
                        String.format(Locale.getDefault(), "$ %s,-", productItem?.price.toString())
                    Picasso.get().load(productItem?.mainImage).into(ivProduct)
                    buttonHeart.setOnClickListener {
                        if (productItem != null) {
                            onAddToFavouriteClickListeners?.invoke(productItem)
                        }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if(item?.isFavorite == true){
            VIEW_TYPE_FAVORITE
        }else {
            VIEW_TYPE_NOT_FAVORITE
        }
    }

    companion object {
        const val VIEW_TYPE_FAVORITE = 0
        const val VIEW_TYPE_NOT_FAVORITE = 1
    }
}