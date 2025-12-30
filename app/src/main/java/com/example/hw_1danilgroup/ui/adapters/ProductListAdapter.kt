package com.example.hw_1danilgroup.ui.adapters

import android.R.attr.onClick
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.example.hw_1danilgroup.data.models.ProductDto
import com.example.hw_1danilgroup.databinding.ItemProductBinding
import com.example.hw_1danilgroup.domain.models.Product

class ProductListAdapter(private val onClick: (Product) -> Unit):
    ListAdapter<Product,ProductListAdapter.ProductViewHolder>(ProductDiffUtilCallback()){

    private var items: List<Product> = emptyList()


    class ProductDiffUtilCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            with(binding) {
                tvTitle.text = product.title
                tvCategory.text = product.category
                tvPrice.text = "${product.price} $"
                ivProduct.load(product.image) {
                    crossfade(true)
                }

                root.setOnClickListener {
                    onClick(product)
                }

            }

        }
    }
}