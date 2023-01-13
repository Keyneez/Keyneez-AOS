package com.keyneez.presentation.main.home.recommend

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.keyneez.data.model.response.ResponseContentDto
import com.keyneez.presentation.main.detail.DetailActivity
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.databinding.ItemHomeBinding

class RecommendAdapter : RecyclerView.Adapter<RecommendAdapter.HomeViewHolder>() {
    var data = listOf<ResponseContentDto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    class HomeViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ResponseContentDto) {
            binding.content = item
            binding.btnHomeHeart.isSelected = item.liked
            binding.root.setOnSingleClickListener {
                val intent = Intent(binding.root.context, DetailActivity::class.java)
                intent.putExtra("contentId", item.key)
                ContextCompat.startActivity(binding.root.context, intent, null)
            }
        }
    }
}
