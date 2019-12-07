package com.passwd.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.passwd.R
import kotlinx.android.synthetic.main.dialog_create_password.colorSelector

class CreatePasswordDialog : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_create_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        colorSelector.setupColorList(
            arrayOf(R.color.colorAccent,
                R.color.colorDark,
                R.color.colorGray,
                R.color.colorAccent)
        )
    }
}