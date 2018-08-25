package com.example.hiroshi.rxtubeapp.ui.player

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.example.hiroshi.rxtubeapp.R
import com.example.hiroshi.rxtubeapp.databinding.FragmentPlayerBinding
import com.example.hiroshi.rxtubeapp.ui.searchitems.SearchItemsViewModel

import org.koin.android.architecture.ext.viewModel

class PlayerFragment : Fragment() {

    private val viewModel by viewModel<PlayerViewModel>()
    private lateinit var binding: FragmentPlayerBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            DataBindingUtil.inflate<FragmentPlayerBinding>(inflater, R.layout.fragment_player, container, false).also {
                binding = it
            }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
    }



    companion object {
        fun newInstance() =
                PlayerFragment()
    }
}
