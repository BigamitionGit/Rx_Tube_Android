package com.example.hiroshi.rxtubeapp.ui.searchitems

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hiroshi.rxtubeapp.databinding.FragmentSearchItemsBinding
import com.example.hiroshi.rxtubeapp.databinding.ItemSearchChannelBinding
import com.example.hiroshi.rxtubeapp.databinding.ItemSearchVideoBinding
import com.example.hiroshi.rxtubeapp.R
import org.koin.android.architecture.ext.viewModel
import java.util.*
import kotlin.collections.ArrayList

class SearchItemsFragment : Fragment() {


    private val viewModel by viewModel<SearchItemsViewModel>()
    private lateinit var binding: FragmentSearchItemsBinding
    private val adapter = SearchItemsAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        DataBindingUtil.inflate<FragmentSearchItemsBinding>(inflater, R.layout.fragment_search_items, container, false).also {
            binding = it
        }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter
        viewModel.searchItemDataSource.observe(this, Observer {
            adapter.run {
                it ?: return@Observer
                viewModels = it.viewModels
                notifyDataSetChanged()
            }
        })

    }


    companion object {
        fun newInstance() =
                SearchItemsFragment()
    }
}

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
        }
    }

    override fun getItemViewType(position: Int): Int {
        val viewModel = viewModels[position]
        return viewModel.getViewType().id
    }

    override fun getItemCount(): Int = viewModels.count()


    private class VideoViewHolder(val binding: ItemSearchVideoBinding) : RecyclerView.ViewHolder (binding.root) {}
    private class ChannelViewHolder(val binding: ItemSearchChannelBinding) : RecyclerView.ViewHolder (binding.root) {}
}