package com.todo.mvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.todo.mvvm.databinding.ItemDogListBinding
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
class DogListAdapter constructor(private val listener: OnItemClickListener) :
    ListAdapter<String, DogListAdapter.DogListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogListViewHolder {
        val binding =
            ItemDogListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogListViewHolder(binding)
    }

    override fun onBindViewHolder(holderRecipe: DogListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holderRecipe.bind(currentItem)
    }

    inner class DogListViewHolder(private val binding: ItemDogListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position))
                    }
                }
            }
        }

        fun bind(breed: String) {
            binding.apply {
                breedName = breed
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(breed: String)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem.contentEquals(newItem)
            }
        }
    }
}