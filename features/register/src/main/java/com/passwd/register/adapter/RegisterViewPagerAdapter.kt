package com.passwd.register.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.passwd.register.OnboardingInformativeFragment
import com.passwd.register.RegisterFragment

class RegisterViewPagerAdapter(
    private val fragmentList: List<Fragment>,
    fragment: Fragment
) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment =
        when (fragmentList[position]) {
            is OnboardingInformativeFragment -> OnboardingInformativeFragment.newInstance()
            is RegisterFragment -> RegisterFragment.newInstance()
            else -> throw IllegalStateException("Invalid adapter position")
        }



}