package com.keyneez.presentation.main.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.keyneez.data.model.response.ResponseGetSearchDto
import com.keyneez.presentation.main.detail.DetailActivity
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
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailActivity::class.java)
                intent.putExtra("contentId", item.key)
                ContextCompat.startActivity(binding.root.context, intent, null)
            }
        }
    }
}
