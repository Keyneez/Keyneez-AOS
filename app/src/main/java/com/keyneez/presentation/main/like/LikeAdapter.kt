package com.keyneez.presentation.main.like

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.keyneez.data.model.response.ResponseLikeDto
import com.keyneez.presentation.main.detail.DetailActivity
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
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailActivity::class.java)
                intent.putExtra("key", item.key)
                ContextCompat.startActivity(binding.root.context, intent, null)
            }
        }
    }
}
