package com.example.hiroshi.rxtubeapp.ui.player

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hiroshi.rxtubeapp.R
import com.example.hiroshi.rxtubeapp.databinding.ItemRelatedVideoBinding

class RelatedVideosAdapter(var viewModels: List<RelatedVideoViewModel>) : RecyclerView.Adapter<RelatedVideosAdapter.RelatedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedVideosAdapter.RelatedViewHolder {

        return RelatedViewHolder(DataBindingUtil.inflate<ItemRelatedVideoBinding>(
                LayoutInflater.from(parent.context), R.layout.item_related_video, parent, false))

    }

    override fun onBindViewHolder(holder: RelatedVideosAdapter.RelatedViewHolder, position: Int) {

        holder.binding.viewModel = viewModels[position]
    }

    override fun getItemCount(): Int = viewModels.count()


    inner class RelatedViewHolder(val binding: ItemRelatedVideoBinding) : RecyclerView.ViewHolder (binding.root)
}