package com.passwd.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.passwd.R
import com.passwd.common.extension.showShortToast
import com.passwd.databinding.ActivityMainBinding
import com.passwd.ui.create.CreatePasswordDialog
import kotlinx.android.synthetic.main.activity_main.recyclerView
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), CreatePasswordDialog.CreatePasswordListener {

    private lateinit var binding: ActivityMainBinding
    private val adapter: HomeRecyclerAdapter by currentScope.inject()
    private val viewModel: HomeViewModel by currentScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataBinding()
        setupRecycler()
        observeList()
        observeError()
        observeCreatePassword()
        viewModel.fetchPasswords(true)
    }

    private fun observeCreatePassword() {
        viewModel
            .showCreatePasswordDialog
            .observe(this, Observer {
                it.getContentIfNotHandled()?.let {
                    CreatePasswordDialog().show(supportFragmentManager, PASSWORD_DIALOG)
                }
            })
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
            })
    }

    private fun observeError() {
        viewModel
            .error
            .observe(this, Observer { error ->
                error.getContentIfNotHandled()?.let { showShortToast(it) }
            })
    }

    override fun passwordSuccessfullyCreated() {
        viewModel.fetchPasswords(true)
    }

    companion object {
        const val PASSWORD_DIALOG = "PASSWORD_DIALOG"
    }
}
