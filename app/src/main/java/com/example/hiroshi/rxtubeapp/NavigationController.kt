package com.example.hiroshi.rxtubeapp

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.hiroshi.rxtubeapp.ui.searchitems.SearchItemsFragment

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