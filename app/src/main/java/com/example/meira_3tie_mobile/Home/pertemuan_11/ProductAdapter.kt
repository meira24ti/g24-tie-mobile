package com.example.meira_3tie_mobile.Home.pertemuan_11

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meira_3tie_mobile.databinding.ItemProductBinding

class ProductAdapter(
    private val productList: List<ProductModel>,
    private val onItemClick: (ProductModel) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(
        val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {

        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {
        val item = productList[position]

        with(holder.binding) {

            tvProductName.text = item.name
            tvProductPrice.text = item.price

            Glide.with(imgProduct)
                .load(item.imageUrl)
                .placeholder(
                    android.R.drawable.ic_menu_gallery
                )
                .error(
                    android.R.drawable.ic_menu_report_image
                )
                .centerCrop()
                .into(imgProduct)

            root.setOnClickListener {
                onItemClick(item)
            }
            Log.e("output", item.name)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
