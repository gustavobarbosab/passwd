package com.passwd.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.gustavobarbosab.moduleinjection.KoinModuleConfig
import com.gustavobarbosab.moduleinjection.KoinModuleInjection
import org.koin.core.scope.Scope

abstract class BaseFragment<B : ViewDataBinding, V : ViewModel> : Fragment() {

    protected lateinit var viewModel: V
    protected lateinit var binding: B

    open var koinModuleConfig: KoinModuleConfig? = null
    private lateinit var moduleInjection: KoinModuleInjection
    val scopeFragment: Scope
        get() = moduleInjection.primaryScope

    abstract val layoutId: Int

    open fun beforeCreatedView(view: View) {}
    open fun afterCreateView(view: View) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        koinModuleConfig?.let { moduleInjection = KoinModuleInjection(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        val view = binding.root
        beforeCreatedView(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        afterCreateView(view)
    }
}