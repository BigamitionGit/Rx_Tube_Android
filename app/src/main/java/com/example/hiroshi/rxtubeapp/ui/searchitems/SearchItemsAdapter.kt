package com.example.hiroshi.rxtubeapp.ui.searchitems

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hiroshi.rxtubeapp.databinding.ItemSearchChannelBinding
import com.example.hiroshi.rxtubeapp.databinding.ItemSearchPlaylistBinding
import com.example.hiroshi.rxtubeapp.databinding.ItemSearchVideoBinding

class SearchItemsAdapter(var viewModels: List<SearchItemDataSource.ViewModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {

        parent ?: return null
        val viewModelType = SearchItemDataSource.ViewModel.ViewType.from(viewType)
        when (viewModelType) {
            SearchItemDataSource.ViewModel.ViewType.Video -> {
                val binding = ItemSearchVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return VideoViewHolder(binding)
            }

            SearchItemDataSource.ViewModel.ViewType.Channel -> {
                val binding = ItemSearchChannelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ChannelViewHolder(binding)
            }

            SearchItemDataSource.ViewModel.ViewType.Playlist -> {
                val binding = ItemSearchPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return PlaylistViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        val viewModel = viewModels[position]

        when {
            holder is VideoViewHolder && viewModel is SearchItemDataSource.ViewModel.Video -> {
                holder.binding.video = viewModel
            }

            holder is ChannelViewHolder && viewModel is SearchItemDataSource.ViewModel.Channel -> {
                holder.binding.channel = viewModel
            }

            holder is PlaylistViewHolder && viewModel is SearchItemDataSource.ViewModel.Playlist -> {
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