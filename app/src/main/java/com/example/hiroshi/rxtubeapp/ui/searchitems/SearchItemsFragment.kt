package com.example.hiroshi.rxtubeapp.ui.searchitems

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hiroshi.rxtubeapp.databinding.FragmentSearchItemsBinding
import com.example.hiroshi.rxtubeapp.R
import com.example.hiroshi.rxtubeapp.data.db.model.YoutubeSearchCondition
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchItemsFragment : Fragment() {


    private val viewModel by sharedViewModel<SearchItemsViewModel>()
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

        val youtubeSearchCondition = SearchItemsFragmentArgs.fromBundle(arguments).searchCondition as? YoutubeSearchCondition
        if (youtubeSearchCondition != null) {
            viewModel.search(youtubeSearchCondition)
        } else {
            viewModel.onCreate()
        }

        lifecycle.addObserver(viewModel)
    }


    companion object {
        fun newInstance() =
                SearchItemsFragment()
    }
}