package com.top1shvetsvadim1.testebs.presentation.adapters.favoriteScreenAdpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import com.top1shvetsvadim1.testebs.R
import com.top1shvetsvadim1.testebs.databinding.ItemProductBinding
import com.top1shvetsvadim1.testebs.databinding.ItemProductFavoriteBinding
import com.top1shvetsvadim1.testebs.domain.ProductItem
import com.top1shvetsvadim1.testebs.presentation.adapters.favoriteScreenAdpater.callbacks.FavoritDiffCallback
import com.top1shvetsvadim1.testebs.presentation.adapters.favoriteScreenAdpater.viewHolders.ItemViewHolder
import java.util.*

class FavoriteAdapter : ListAdapter<ProductItem, ItemViewHolder>(FavoritDiffCallback) {

    var onProductItemClickListeners: ((ProductItem) -> Unit)? = null
    var onRemoveFromFavouriteClickListeners : ((ProductItem) ->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val layout = when (viewType) {
            VIEW_TYPE_FAVORITE -> R.layout.item_product_favorite
            VIEW_TYPE_NOT_FAVORITE -> R.layout.item_product
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
            onProductItemClickListeners?.invoke(productItem)
        }
        when (binding) {
            is ItemProductFavoriteBinding -> {
                with(binding) {
                    with(productItem) {
                        tvProductName.text = name
                        tvSize.text = size
                        tvPrice.text =
                            String.format(Locale.getDefault(), "$ %s,-", price.toString())
                        tvSmallPrice.text =
                            String.format(Locale.getDefault(), "$ %s,-", price.toString())
                        Picasso.get().load(mainImage).into(ivProduct)
                        buttonHeart.setOnClickListener {
                            onRemoveFromFavouriteClickListeners?.invoke(productItem)
                        }
                    }
                }
            }
            is ItemProductBinding -> {
                with(binding) {
                    with(productItem) {
                        tvProductName.text = name
                        tvSize.text = size
                        tvPrice.text =
                            String.format(Locale.getDefault(), "$ %s,-", price.toString())
                        tvSmallPrice.text =
                            String.format(Locale.getDefault(), "$ %s,-", price.toString())
                        Picasso.get().load(mainImage).into(ivProduct)
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.isFavorite) {
            VIEW_TYPE_FAVORITE
        } else {
            VIEW_TYPE_NOT_FAVORITE
        }
    }

    companion object {
        const val VIEW_TYPE_FAVORITE = 0
        const val VIEW_TYPE_NOT_FAVORITE = 1
    }
}