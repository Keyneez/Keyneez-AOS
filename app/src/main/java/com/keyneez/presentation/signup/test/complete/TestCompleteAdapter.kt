package com.keyneez.presentation.signup.test.complete

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keyneez.presentation.main.like.LikeAdapter
import com.lab.keyneez.databinding.ItemTestCompleteItemBinding

class TestCompleteAdapter : RecyclerView.Adapter<TestCompleteAdapter.get>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TestCompleteAdapter.getViewHolder {
        val binding =
            ItemTestCompleteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TestCompleteAdapter.getViewHolder(binding)
    }
    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: LikeAdapter.getViewHolder, position: Int) {
        holder.bind(data[position])
    }
}
