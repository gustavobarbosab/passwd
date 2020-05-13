package com.passwd.createpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.passwd.core.di.KoinModuleInjection
import com.passwd.common.extension.setNavigationResult
import com.passwd.core.di.KoinModuleConfig
import com.passwd.createpassword.databinding.DialogCreatePasswordBinding
import com.passwd.createpassword.di.CreatePasswordModule
import io.github.gustavobarbosab.domain.model.PasswordCreationResult
import kotlinx.android.synthetic.main.dialog_create_password.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class CreatePasswordDialog : BottomSheetDialogFragment() {

    private val moduleInjection = KoinModuleInjection(
        KoinModuleConfig(
            "CREATE_PASSWORD",
            CreatePasswordModule.SCOPE_NAME,
            listOf(CreatePasswordModule.module)
        )
    )
    lateinit var viewModel: CreatePasswordViewModel
    lateinit var binding: DialogCreatePasswordBinding

    private val scopeFragment
        get() = moduleInjection.primaryScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = scopeFragment.getViewModel(this)
        binding =
            DataBindingUtil
                .inflate(
                    inflater,
                    R.layout.dialog_create_password,
                    container,
                    false
                )
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupColorSelector()
        observeViewModel()
        setupListeners()
    }

    private fun setupListeners() {
        closeButton.setOnClickListener { this.dismiss() }
    }

    private fun observeViewModel() {
        viewModel
            .genericError
            .observe(this, Observer {
                it?.getContentIfNotHandled()?.let {
                    Toast.makeText(context, "Houve um erro desconhecido! :[", Toast.LENGTH_SHORT)
                        .show()
                }
            })

        viewModel
            .createPasswordSuccess
            .observe(this, Observer {
                it?.getContentIfNotHandled()?.let {
                    this.dismiss()
                    setNavigationResult(result = PasswordCreationResult.RESULT_SUCCESS)
                    Toast.makeText(context, "Senha salva com sucesso!", Toast.LENGTH_SHORT).show()
                }
            })

        viewModel
            .validationFieldsError
            .observe(this, Observer {
                it?.getContentIfNotHandled()?.let {
                    Toast.makeText(
                        context,
                        "Preencha todos os campos para prosseguir!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    private fun setupColorSelector() {
        val colors = arrayOf(
            R.color.colorOne,
            R.color.colorTwo,
            R.color.colorThree,
            R.color.colorFour,
            R.color.colorFive,
            R.color.colorSix,
            R.color.colorSeven,
            R.color.colorEight,
            R.color.colorNine,
            R.color.colorTeen,
            R.color.colorEleven,
            R.color.colorTwelve
        )
        colorSelector.colorSelectedClickListener = this::onColorSelected
        colorSelector.setupColorList(colors)
    }

    private fun onColorSelected(color: Int) {
        viewModel.colorSelected = color
    }

    override fun onDestroy() {
        moduleInjection.unloadModules()
        super.onDestroy()
    }
}