package com.passwd.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.passwd.R
import com.passwd.common.extension.showShortToast
import com.passwd.common.swipecontroler.ButtonProperties
import com.passwd.common.swipecontroler.SwipeController
import com.passwd.common.swipecontroler.SwipeControllerProperties
import com.passwd.databinding.ActivityHomeBinding
import com.passwd.ui.base.BaseActivity
import com.passwd.ui.create.CreatePasswordDialog
import com.passwd.ui.home.model.HomeStates
import kotlinx.android.synthetic.main.activity_home.homeContainer
import kotlinx.android.synthetic.main.activity_home.recyclerView
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.getViewModel

class       HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), CreatePasswordDialog.CreatePasswordListener {

    private val adapter: HomeRecyclerAdapter by currentScope.inject()

    override val layoutId: Int = R.layout.activity_home
    override var requireAuthentication: Boolean = true

    private val rightButtonRecycler = ButtonProperties(
        R.color.colorDelete,
        R.string.home_list_button_right) { position -> viewModel.deletePassword(adapter.getPassword(position)) }

    private val undoDeleteListener = View.OnClickListener {
        viewModel.undoDeleteLastPassword()
    }

    private val deleteSnackBar by lazy {
        Snackbar.make(homeContainer, R.string.home_snackbar_delete_message, Snackbar.LENGTH_SHORT).apply {
            setAction(R.string.home_snackbar_undo, undoDeleteListener)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = currentScope.getViewModel(this)
        binding.viewModel = viewModel
        setupRecyclerView()
        observeList()
        observeError()
        observeCreatePassword()
        observeViewStates()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        binding.swipeController = SwipeController(SwipeControllerProperties(rightButton = rightButtonRecycler))
    }

    private fun observeViewStates() {
        viewModel
            .viewState
            .observe(this, Observer {
                when (it.getContentIfNotHandled()) {
                    HomeStates.ShowLoading -> showLoading()
                    HomeStates.HideLoading -> hideLoading()
                    HomeStates.DeleteSuccess -> onDeleteSuccess()
                    HomeStates.FetchSuccess -> onFetchListSuccess()
                }
            })
    }

    private fun showLoading() {
    }

    private fun hideLoading() {
    }

    private fun onDeleteSuccess() {
        deleteSnackBar.show()
    }

    private fun onFetchListSuccess() {
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
        fun newIntent(context: Context): Intent = Intent(context, HomeActivity::class.java)

        const val PASSWORD_DIALOG = "PASSWORD_DIALOG"
    }
}
