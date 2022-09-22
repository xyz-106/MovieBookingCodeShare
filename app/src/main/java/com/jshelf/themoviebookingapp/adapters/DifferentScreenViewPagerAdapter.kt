package com.jshelf.themoviebookingapp.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jshelf.themoviebookingapp.delegates.PassDataDelegate
import com.jshelf.themoviebookingapp.fragments.LoginFragment
import com.jshelf.themoviebookingapp.fragments.SingUpFragment

class DifferentScreenViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val loginDelegate: PassDataDelegate
) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                // val loginFragment = LoginFragment(loginDelegate)

                val bundle = Bundle()
                bundle.putString(LoginFragment.KEY_HOME_DESCRIPTION, "This is Home Fragment")
                LoginFragment().arguments = bundle
                return LoginFragment()
            }

            else -> {
                // val sigupFragment = SingUpFragment(loginDelegate)
                val sigupFragment = SingUpFragment()
                return sigupFragment
            }
        }
    }
}