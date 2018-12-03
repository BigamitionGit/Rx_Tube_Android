package com.example.hiroshi.rxtubeapp.ui.channeldetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hiroshi.rxtubeapp.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ChannelDetailFragment : Fragment() {

    private val viewModel: ChannelDetailViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_channel_detail, container, false)
    }

    companion object {

        fun newInstance() =
                ChannelDetailFragment()
    }
}
