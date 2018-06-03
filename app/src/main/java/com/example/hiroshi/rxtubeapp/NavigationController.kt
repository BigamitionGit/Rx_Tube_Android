package com.example.hiroshi.rxtubeapp

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.example.hiroshi.rxtubeapp.ui.searchitems.SearchItemsFragment
import kotlinx.android.synthetic.main.activity_main.view.*

class NavigationController(private val activity: AppCompatActivity) {

    private val containerId: Int = R.id.content
    private val fragmentManager: FragmentManager = activity.supportFragmentManager

    fun navigateToHome() {

    }

    fun navigateToSearch() {

        replaceFragment(SearchItemsFragment.newInstance())
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = fragmentManager
                .beginTransaction()
                .replace(containerId, fragment)

        if (fragmentManager.isStateSaved) {
            transaction.commitAllowingStateLoss()
        } else {
            transaction.commit()
        }
    }

    fun navigateToMainActivity() {
        MainActivity.start(activity)
    }
}