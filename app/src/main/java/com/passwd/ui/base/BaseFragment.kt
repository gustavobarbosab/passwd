package com.passwd.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import org.koin.android.ext.android.getKoin
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.core.scope.Scope

abstract class BaseFragment<B : ViewDataBinding, V : ViewModel> : Fragment() {
    protected lateinit var viewModel: V
    protected lateinit var binding: B

    private val loadModules by lazy { loadKoinModules(modules) }
    open var modules: List<Module> = emptyList()
    open lateinit var fragmentScope: Scope

    abstract val layoutId: Int

    open fun configureComponents(view: View) {}
    open fun initializeComponents(view: View) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadModules
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        val view = binding.root
        initializeComponents(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureComponents(view)
    }

    override fun onDestroy() {
        fragmentScope.close()
        unloadKoinModules(modules)
        super.onDestroy()
    }
}