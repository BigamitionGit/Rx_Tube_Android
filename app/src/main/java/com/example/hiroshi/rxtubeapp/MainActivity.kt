package com.example.hiroshi.rxtubeapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.hiroshi.rxtubeapp.databinding.ActivityMainBinding
import com.example.hiroshi.rxtubeapp.ui.channeldetail.ChannelDetailFragment
import com.example.hiroshi.rxtubeapp.ui.channeldetail.ChannelDetailViewModel
import com.example.hiroshi.rxtubeapp.ui.home.HomeFragmentDirections
import com.example.hiroshi.rxtubeapp.ui.player.PlayerViewModel
import com.example.hiroshi.rxtubeapp.ui.searchcondition.SearchConditionViewModel
import com.example.hiroshi.rxtubeapp.ui.searchitems.SearchItemsFragmentDirections
import com.example.hiroshi.rxtubeapp.ui.searchitems.SearchItemsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private val searchItemsViewModel: SearchItemsViewModel by viewModel()
    private val searchConditionViewModel: SearchConditionViewModel by viewModel()
    private val channelDetailViewModel: ChannelDetailViewModel by viewModel()
    private val playerViewModel: PlayerViewModel by viewModel()

    private val navController: NavController by lazy { findNavController(this, R.id.nav_host_fragment) }

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(mainViewModel)

        NavigationUI.setupActionBarWithNavController(this, navController)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
        setupBottomNavigation(savedInstanceState)

        playerViewModel.showChannelDetail.observe(this, Observer { channel ->
            navController.currentDestination?.let { destination ->
                if (destination.id == R.id.navigation_searchItems) {
                    // transition
                    navController.navigate(SearchItemsFragmentDirections.showChannel().setChannelDetail(channel))
                } else if (destination.id == R.id.navigation_home) {
                    // transition
                    navController.navigate(HomeFragmentDirections.showChannel().setChannelDetail(channel))
                } else if (destination.id == R.id.navigation_channelDetail) {
                    // update channel detail
                    channelDetailViewModel.channelDidTap(channel)
                }
            }


        })




    }

    private fun setupBottomNavigation(savedInstanceState: Bundle?) {

        binding.bottomNavigation.setOnNavigationItemSelectedListener({ item ->
            val navigationItem = BottomNavigationItem
                    .forId(item.itemId)

            setupToolbar(navigationItem)
            true
        })

        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.navigation_home
        }

        binding.bottomNavigation.setOnNavigationItemReselectedListener { item ->
            val navigationItem = BottomNavigationItem.forId(item.itemId)
            val fragment = supportFragmentManager.findFragmentByTag(navigationItem.name)
            if (fragment is BottomNavigationItem.OnReselectedListener) {
                fragment.onReselected()
            }
        }

    }

    private fun setupToolbar(navigationItem: BottomNavigationItem) {
        supportActionBar?.apply {
            title = if (navigationItem.imageRes != null) {
                setDisplayShowHomeEnabled(true)
                setIcon(navigationItem.imageRes)
                null
            } else {
                setDisplayShowHomeEnabled(false)
                setIcon(null)
                getString(navigationItem.titleRes!!)
            }
        }
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    enum class BottomNavigationItem(@MenuRes val menuId: Int,
                                    @StringRes val titleRes: Int?,
                                    @DrawableRes val imageRes: Int?,
                                    val isUseToolbarElevation: Boolean) {

        SEARCH(R.id.navigation_search, R.string.search_title, null, false);

        interface OnReselectedListener {
            fun onReselected()
        }

        companion object {
            fun forId(@IdRes id: Int): BottomNavigationItem {
                return values().first { it.menuId == id }
            }
        }
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, MainActivity::class.java)

        fun start(context: Context) {
            createIntent(context).let {
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(it)
            }
        }
    }
}
