package com.passwd.home

import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.gustavobarbosab.databinding.BaseFragment
import com.gustavobarbosab.extension.getNavigationResult
import com.gustavobarbosab.extension.showShortToast
import com.gustavobarbosab.swipecontroller.ButtonProperties
import com.gustavobarbosab.swipecontroller.SwipeController
import com.gustavobarbosab.swipecontroller.SwipeControllerConfiguration
import com.gustavobarbosab.swipecontroller.SwipeControllerProperties
import com.passwd.R.color.colorDelete
import com.passwd.home.databinding.FragmentHomeBinding
import com.passwd.home.di.HomeModule
import com.passwd.home.model.HomeStates
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.module.Module

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val layoutId: Int = R.layout.fragment_home
    override fun modules(): List<Module> = listOf(HomeModule.module)

    private val adapter by inject<HomeRecyclerAdapter>()
    private val viewModel by viewModel<HomeViewModel>()

    private val rightButtonRecycler = ButtonProperties(
        colorDelete,
        R.string.home_list_button_right
    ) { position -> viewModel.deletePassword(adapter.getPassword(position)) }

    private val undoDeleteListener = View.OnClickListener { viewModel.undoDeleteLastPassword() }

    private val deleteSnackBar by lazy {
        Snackbar.make(homeContainer, R.string.home_snackbar_delete_message, Snackbar.LENGTH_SHORT)
            .apply { setAction(R.string.home_snackbar_undo, undoDeleteListener) }
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
        recyclerView.adapter = adapter
        SwipeControllerConfiguration.configure(
            recyclerView,
            SwipeController(SwipeControllerProperties(rightButton = rightButtonRecycler))
        )
    }

    private fun observeViewStates() {
        viewModel
            .viewState
            .observe(this, Observer {
                when (it.getContentIfNotHandled()) {
                    HomeStates.ShowLoading -> {
                    }
                    HomeStates.HideLoading -> {
                    }
                    HomeStates.DeleteSuccess -> deleteSnackBar.show()
                    HomeStates.FetchSuccess -> {
                    }
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

        getNavigationResult<String>()
            ?.observe(this, Observer {
                viewModel.fetchPasswords(true)
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
}