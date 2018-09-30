package com.example.hiroshi.rxtubeapp.ui.searchcondition

import android.arch.lifecycle.Observer
import android.content.Context
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.hiroshi.rxtubeapp.R
import com.example.hiroshi.rxtubeapp.databinding.FragmentSearchConditionBinding

import org.koin.android.architecture.ext.viewModel

class SearchConditionFragment : Fragment() {

    private val viewModel by viewModel<SearchConditionViewModel>()
    private lateinit var binding: FragmentSearchConditionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            DataBindingUtil.inflate<FragmentSearchConditionBinding>(inflater, R.layout.fragment_search_condition, container, false)
                    .also {
                        binding = it
                    }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        lifecycle.addObserver(viewModel)

        viewModel.search.observe(this, Observer { condition ->
            navController().navigate(SearchConditionFragmentDirections.showSearchItems().setSearchCondition(condition))
        })
    }

    fun navController() = findNavController(this)



    companion object {
        fun newInstance() =
                SearchConditionFragment()
    }
}
