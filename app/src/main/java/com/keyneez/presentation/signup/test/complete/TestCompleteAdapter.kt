package com.keyneez.presentation.signup.test.complete

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keyneez.data.entity.ItemData
import com.lab.keyneez.databinding.ItemTestCompleteItemBinding

class TestCompleteAdapter : RecyclerView.Adapter<TestCompleteAdapter.ItemViewHolder>() {
    var data = mutableListOf<ItemData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemTestCompleteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    class ItemViewHolder(private val binding: ItemTestCompleteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ItemData) {
            binding.data = item
        }
    }
}
