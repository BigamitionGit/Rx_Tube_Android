package com.example.hiroshi.rxtubeapp.ui.searchitems

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hiroshi.rxtubeapp.databinding.ItemSearchChannelBinding
import com.example.hiroshi.rxtubeapp.databinding.ItemSearchPlaylistBinding
import com.example.hiroshi.rxtubeapp.databinding.ItemSearchVideoBinding

class SearchItemsAdapter(var viewModels: List<SearchItemViewModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {

        parent ?: return null
        val viewModelType = SearchItemViewModel.ViewType.from(viewType)
        when (viewModelType) {
            SearchItemViewModel.ViewType.Video -> {
                val binding = ItemSearchVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return VideoViewHolder(binding)
            }

            SearchItemViewModel.ViewType.Channel -> {
                val binding = ItemSearchChannelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ChannelViewHolder(binding)
            }

            SearchItemViewModel.ViewType.Playlist -> {
                val binding = ItemSearchPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return PlaylistViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        val viewModel = viewModels[position]

        when {
            holder is VideoViewHolder && viewModel is SearchItemViewModel.Video -> {
                holder.binding.video = viewModel
            }

            holder is ChannelViewHolder && viewModel is SearchItemViewModel.Channel -> {
                holder.binding.channel = viewModel
            }

            holder is PlaylistViewHolder && viewModel is SearchItemViewModel.Playlist -> {
                holder.binding.playlist = viewModel
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val viewModel = viewModels[position]
        return viewModel.getViewType().id
    }

    override fun getItemCount(): Int = viewModels.count()


    private class VideoViewHolder(val binding: ItemSearchVideoBinding) : RecyclerView.ViewHolder (binding.root) {}
    private class ChannelViewHolder(val binding: ItemSearchChannelBinding) : RecyclerView.ViewHolder (binding.root) {}
    private class PlaylistViewHolder(val binding: ItemSearchPlaylistBinding) : RecyclerView.ViewHolder (binding.root) {}
}