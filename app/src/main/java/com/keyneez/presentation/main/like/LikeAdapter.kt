package com.keyneez.presentation.main.like

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.keyneez.data.entity.LikeData
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ItemLikeContentBinding

class LikeAdapter : RecyclerView.Adapter<LikeAdapter.getViewHolder>() {
    var data = listOf<LikeData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): getViewHolder {
        val binding =
            ItemLikeContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return getViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: getViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class getViewHolder(private val binding: ItemLikeContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LikeData) {
            binding.ivLikeBackground.load(item.background) {
                fallback(R.drawable.img_like_background)
                placeholder(R.drawable.img_like_background)
                transformations(RoundedCornersTransformation(14f))
            }
            binding.tvLikeDate.text = item.date
            binding.tvLikeTitle.text = item.title
        }
    }
}
