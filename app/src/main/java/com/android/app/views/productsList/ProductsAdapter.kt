package com.android.app.views.productsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.app.databinding.ItemProductBinding
import com.android.app.network.model.Product
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class ProductsAdapter @Inject constructor() : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private var data: List<Product> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemBinding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(itemBinding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(data[position])

    fun setData(data: List<Product>) {
        this.data = data
        notifyDataSetChanged()
    }

    class ProductViewHolder(private val itemBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Product) {
            itemBinding.nameTv.text = item.name
            itemBinding.quantityTv.text = item.quantity.toString()
            itemBinding.priceTv.text = item.price.toString()
        }
    }
}