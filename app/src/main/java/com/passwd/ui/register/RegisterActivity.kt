package com.passwd.ui.register

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.passwd.R
import com.passwd.databinding.ActivityRegisterBinding
import com.passwd.ui.base.BaseActivity

class RegisterActivity : BaseActivity<ActivityRegisterBinding, ViewModel>() {

    override val layoutId: Int = R.layout.activity_register
    override var requireAuthentication: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
