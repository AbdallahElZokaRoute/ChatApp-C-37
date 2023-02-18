package com.route.chatappc37.ui.base

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.route.chatappc37.R

abstract class BaseActivity<DATA_BINDING : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {
    lateinit var viewDataBinding: DATA_BINDING
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = initViewModel()
    }

    abstract fun getLayoutId(): Int
    abstract fun initViewModel(): VM


    fun showDialogMessage(
        message: String?,
        positiveButtonText: String?,
        positiveButtonClick: DialogInterface.OnClickListener
    ) {
        val alertDialogBuilder = AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton(positiveButtonText, positiveButtonClick)
        alertDialogBuilder.show()

    }
}
