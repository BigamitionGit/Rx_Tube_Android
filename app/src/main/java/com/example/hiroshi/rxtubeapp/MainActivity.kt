package com.example.hiroshi.rxtubeapp

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.MenuRes
import android.support.annotation.StringRes
import android.view.MenuItem
import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.NavAction
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.hiroshi.rxtubeapp.data.db.dbservice.Database
import com.example.hiroshi.rxtubeapp.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy { findNavController(this, R.id.nav_host_fragment) }

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NavigationUI.setupActionBarWithNavController(this, navController)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
        setupBottomNavigation(savedInstanceState)
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
