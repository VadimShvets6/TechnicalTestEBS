package com.top1shvetsvadim1.testebs.presentation.adapters.mainScreenAdapter.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.top1shvetsvadim1.testebs.domain.ProductItem

object DiffUtilsCallback : DiffUtil.ItemCallback<ProductItem>() {
    override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
        return oldItem == newItem
    }
}