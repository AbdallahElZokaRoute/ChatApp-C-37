package com.route.chatappc37.ui.loading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.route.chatappc37.R
import com.route.chatappc37.databinding.DialogLoadingBinding

class LoadingDialog : DialogFragment() {
    lateinit var dataBinding: DialogLoadingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_loading, container, false)
        return dataBinding.root
    }

}