package com.example.hw_1danilgroup.ui.product_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.example.hw_1danilgroup.data.models.ProductDto
import com.example.hw_1danilgroup.databinding.ItemProductBinding

class ProductListAdapter(private val onClick: (ProductDto) -> Unit) : RecyclerView.Adapter<ProductListAdapter.ProductListHolder>() {

    private var items : List<ProductDto> = emptyList()

    fun submitList(list: List<ProductDto>){
        items = list
        notifyDataSetChanged()

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductListHolder {
        return ProductListHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ProductListHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return  items.size
    }

    inner class ProductListHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductDto){
            with(binding){
                tvTitle.text = product.title
                tvCategory.text = product.category
                tvPrice.text = "${product.price} $"
                ivProduct.load(product.image){
                    crossfade(true)
                }

                root.setOnClickListener {
                    onClick(product)
                }

            }

        }
    }
}