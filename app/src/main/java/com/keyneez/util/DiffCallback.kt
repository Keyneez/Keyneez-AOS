package com.keyneez.util

import androidx.recyclerview.widget.DiffUtil

class DiffCallback<T : Any>(
    val onItemsTheSame: (T, T) -> Boolean,
    val onContentsTheSame: (T, T) -> Boolean
) : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        onItemsTheSame(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        onContentsTheSame(oldItem, newItem)
}
