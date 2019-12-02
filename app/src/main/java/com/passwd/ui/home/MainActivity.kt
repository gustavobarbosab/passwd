package com.passwd.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.passwd.R
import com.passwd.common.extension.showShortToast
import com.passwd.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.recyclerView
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModel()
    val adapter: MainRecyclerAdapter by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataBinding()
        setupRecycler()
        observeList()
        observeError()
        viewModel.fetchPasswords()
    }

    private fun setupDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
    }

    private fun setupRecycler() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun observeList() {
        viewModel
            .passwordList
            .observe(this, Observer {
                adapter.setPasswords(it)
                adapter.notifyDataSetChanged()
                showShortToast("Lista carregada!")
            })
    }

    private fun observeError() {
        viewModel
            .error
            .observe(this, Observer {
                showShortToast(it)
            })
    }
}
