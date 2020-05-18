package com.passwd.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.passwd.register.adapter.RegisterViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_base_register.*

class BaseRegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configViewPager()
    }

    private fun configViewPager() {
        pages.adapter = RegisterViewPagerAdapter(
            arrayListOf(
                OnboardingInformativeFragment.newInstance(),
                RegisterFragment.newInstance()
            ),
            this
        )

        TabLayoutMediator(tabs, pages) { tab, position -> tab.text = "" }.attach()
    }
}
