package com.example.hiroshi.rxtubeapp.ui.searchcondition

import android.content.Context
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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
    }



    companion object {
        fun newInstance() =
                SearchConditionFragment()
    }
}

