package com.passwd.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.passwd.R
import com.passwd.common.extension.showShortToast
import com.passwd.common.swipecontroler.ButtonProperties
import com.passwd.common.swipecontroler.SwipeController
import com.passwd.common.swipecontroler.SwipeControllerProperties
import com.passwd.databinding.ActivityMainBinding
import com.passwd.ui.create.CreatePasswordDialog
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), CreatePasswordDialog.CreatePasswordListener {

    private lateinit var binding: ActivityMainBinding
    private val adapter: HomeRecyclerAdapter by currentScope.inject()
    private val viewModel: HomeViewModel by currentScope.viewModel(this)

    private val leftButtonRecycler = ButtonProperties(
            R.color.colorGreen,
            R.string.home_list_button_left) { pos-> showShortToast("Left $pos")}

    private val rightButtonRecycler = ButtonProperties(
            R.color.colorRed,
            R.string.home_list_button_right) { pos-> showShortToast("Right $pos")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataBinding()
        setupRecyclerView()
        observeList()
        observeError()
        observeCreatePassword()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        binding.swipeController = SwipeController(SwipeControllerProperties(leftButtonRecycler, rightButtonRecycler))
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
        binding = DataBindingUtil.setContentView(this, com.passwd.R.layout.activity_main)
        binding.viewModel = viewModel
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
