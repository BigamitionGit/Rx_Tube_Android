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
import com.example.hiroshi.rxtubeapp.data.db.dbservice.Database
import com.example.hiroshi.rxtubeapp.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val navigationController: NavigationController by inject()

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private fun setupBottomNavigation(savedInstanceState: Bundle?) {
        setBottomNavigationBehavior()

        binding.bottomNavigation.setOnNavigationItemSelectedListener({ item ->
            val navigationItem = BottomNavigationItem
                    .forId(item.itemId)

            setupToolbar(navigationItem)
            navigationItem.navigate(navigationController)
            true
        })
    }

    private fun setBottomNavigationBehavior() {

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

    enum class BottomNavigationItem(@MenuRes val menuId: Int,
                                    @StringRes val titleRes: Int?,
                                    @DrawableRes val imageRes: Int?,
                                    val isUseToolbarElevation: Boolean,
                                    val navigate: (NavigationController) -> Unit) {

        SEARCH(R.id.navigation_search, R.string.search_title, null, false, {
            it.navigateToSearch()
        });

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
