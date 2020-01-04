package com.passwd.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<B : ViewDataBinding, V : ViewModel> : AppCompatActivity() {

    protected lateinit var viewModel: V
    protected lateinit var binding: B

    abstract val layoutId: Int
    abstract fun createViewModel(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = createViewModel()
    }

    override fun onResume() {
        super.onResume()
    }
}