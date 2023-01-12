package com.keyneez.presentation.main.like

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keyneez.data.model.response.ResponseLikeDto
import com.lab.keyneez.databinding.ItemLikeContentBinding

class LikeAdapter : RecyclerView.Adapter<LikeAdapter.getViewHolder>() {
    var data = listOf<ResponseLikeDto>()
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
        fun bind(item: ResponseLikeDto) {
            binding.data = item
        }
    }
}
