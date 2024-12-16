package com.example.ntg_task.ui.character_profile

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ntg_task.databinding.ListItemComicBinding
import com.example.ntg_task.domain.entities.Comic

class ComicsAdapter(private val getComicThumbnail: (Comic) -> Unit) :
    ListAdapter<Comic, ComicsAdapter.ViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Comic>() {
            override fun areItemsTheSame(
                oldItem: Comic,
                newItem: Comic
            ): Boolean {
                return oldItem.resourceURI == newItem.resourceURI
            }

            override fun areContentsTheSame(
                oldItem: Comic,
                newItem: Comic
            ): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comic = getItem(position)
        Log.d("TAG", "onBindViewHolder: $comic")
        if (comic?.thumbnail == null) {
            getComicThumbnail.invoke(comic)
        }

        Glide.with(holder.itemView.context)
            .load(comic.thumbnail?.path + "." + comic.thumbnail?.extension)
            .placeholder(android.R.drawable.ic_menu_report_image)
            .into(holder.binding.ivComic)

    }

    class ViewHolder(val binding: ListItemComicBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemComicBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}