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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            DataBindingUtil.inflate<FragmentSearchItemsBinding>(inflater, R.layout.fragment_search_items, container, false).also {
                binding = it
            }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        lifecycle.addObserver(viewModel)
    }


    companion object {
        fun newInstance() =
                SearchItemsFragment()
    }
}