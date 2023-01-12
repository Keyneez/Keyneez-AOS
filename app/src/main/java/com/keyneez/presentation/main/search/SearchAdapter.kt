package com.keyneez.presentation.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.keyneez.data.entity.SearchData
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ItemSearchContentBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.getViewHolder>() {
    var data = listOf<SearchData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): getViewHolder {
        val binding =
            ItemSearchContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return getViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: getViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class getViewHolder(private val binding: ItemSearchContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchData) {
            binding.ivSearchBackground.load(item.background) {
                fallback(R.drawable.img_like_background)
                placeholder(R.drawable.img_like_background)
                transformations(RoundedCornersTransformation(14f))
            }
            binding.tvSearchDate.text = item.date
            binding.tvSearchTitle.text = item.title
            binding.btnSearchLiked.isSelected = item.liked
        }
    }
}
