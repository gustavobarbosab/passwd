package com.passwd.home

import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.passwd.common.extension.showShortToast
import com.passwd.common.swipecontroler.ButtonProperties
import com.passwd.common.swipecontroler.SwipeController
import com.passwd.common.swipecontroler.SwipeControllerProperties
import com.passwd.core.di.ModuleConfig
import com.passwd.home.databinding.FragmentHomeBinding
import com.passwd.home.di.HomeModule
import com.passwd.home.model.HomeStates
import com.passwd.ui.base.BaseFragment
import com.passwd.ui.create.CreatePasswordDialog
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    CreatePasswordDialog.CreatePasswordListener {

    override val moduleConfig: ModuleConfig = ModuleConfig(
        "HOME_ID",
        HomeModule.SCOPE_NAME,
        listOf(HomeModule.module)
    )
    override val layoutId: Int = R.layout.fragment_home

    lateinit var adapter: HomeRecyclerAdapter

    private val rightButtonRecycler = ButtonProperties(
        com.passwd.R.color.colorDelete,
        R.string.home_list_button_right
    ) { position -> viewModel.deletePassword(adapter.getPassword(position)) }

    private val undoDeleteListener = View.OnClickListener { viewModel.undoDeleteLastPassword() }

    private val deleteSnackBar by lazy {
        Snackbar.make(homeContainer, R.string.home_snackbar_delete_message, Snackbar.LENGTH_SHORT)
            .apply {
                setAction(R.string.home_snackbar_undo, undoDeleteListener)
            }
    }

    override fun beforeCreatedView(view: View) {
        viewModel = fragmentScope.getViewModel(this)
        adapter = fragmentScope.get()
    }

    override fun afterCreateView(view: View) {
        binding.viewModel = viewModel
        setupRecyclerView()
        observeList()
        observeError()
        observeCreatePassword()
        observeViewStates()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        binding.swipeController =
            SwipeController(SwipeControllerProperties(rightButton = rightButtonRecycler))
    }

    private fun observeViewStates() {
        viewModel
            .viewState
            .observe(this, Observer {
                when (it.getContentIfNotHandled()) {
                    HomeStates.ShowLoading -> {}
                    HomeStates.HideLoading -> {}
                    HomeStates.DeleteSuccess -> deleteSnackBar.show()
                    HomeStates.FetchSuccess -> {}
                }
            })
    }

    private fun observeCreatePassword() {
        viewModel
            .showCreatePasswordDialog
            .observe(this, Observer {
                it.getContentIfNotHandled()?.let {
                    val direction = HomeFragmentDirections.actionCreatePassword()
                    findNavController().navigate(direction)
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
                error.getContentIfNotHandled()?.let { context?.showShortToast(it) }
            })
    }

    override fun passwordSuccessfullyCreated() {
        viewModel.fetchPasswords(true)
    }
}