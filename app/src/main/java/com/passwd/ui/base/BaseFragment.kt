package com.passwd.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.passwd.core.di.ModuleConfig
import org.koin.android.ext.android.getKoin
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

abstract class BaseFragment<B : ViewDataBinding, V : ViewModel> : Fragment() {
    protected lateinit var viewModel: V
    protected lateinit var binding: B

    private val loadModules by lazy { loadKoinModules(moduleConfig.modules) }
    lateinit var fragmentScope: Scope

    abstract val moduleConfig: ModuleConfig
    abstract val layoutId: Int

    open fun beforeCreatedView(view: View) {}
    open fun afterCreateView(view: View) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadModules
        fragmentScope = getKoin().getOrCreateScope(moduleConfig.id, named(moduleConfig.name))
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

    override fun onDestroy() {
        fragmentScope.close()
        unloadKoinModules(moduleConfig.modules)
        super.onDestroy()
    }
}