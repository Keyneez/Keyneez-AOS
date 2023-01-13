package com.keyneez.presentation.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keyneez.data.model.response.ResponseGetSearchDto
import com.lab.keyneez.databinding.ItemSearchContentBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.getViewHolder>() {
    var data = listOf<ResponseGetSearchDto>()

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
        fun bind(item: ResponseGetSearchDto) {
            binding.data = item
        }
    }
}
