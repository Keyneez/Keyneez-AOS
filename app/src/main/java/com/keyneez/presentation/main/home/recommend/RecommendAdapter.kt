package com.keyneez.presentation.main.home.recommend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.keyneez.data.entity.HomeData
import com.lab.keyneez.databinding.ItemHomeBinding

class RecommendAdapter : RecyclerView.Adapter<RecommendAdapter.InfoViewHolder>() {
    var data = listOf<HomeData>()

    class InfoViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeData) {
            binding.ivHomeItem.load(item.background)
            binding.tvHomeTitle.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InfoViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bind(data[position])
    }
}
