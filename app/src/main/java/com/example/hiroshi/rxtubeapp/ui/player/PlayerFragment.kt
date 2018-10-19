package com.example.hiroshi.rxtubeapp.ui.player

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.hiroshi.rxtubeapp.R
import com.example.hiroshi.rxtubeapp.databinding.FragmentPlayerBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PlayerFragment : Fragment() {

    private val viewModel by sharedViewModel<PlayerViewModel>()
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
