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
import com.route.chatappc37.ui.loading.LoadingDialog

abstract class BaseActivity<DATA_BINDING : ViewDataBinding, VM : BaseViewModel<*>> :
    AppCompatActivity() {
    lateinit var viewDataBinding: DATA_BINDING
    lateinit var viewModel: VM
    lateinit var loadingDialog: LoadingDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = initViewModel()
        loadingDialog = LoadingDialog()
        viewModel.messageLiveData.observe(this) {
            showDialogMessage(
                it, "ok"
            ) { dialog, which -> dialog.dismiss() }
        }
        viewModel.showProgressBar.observe(this) { showLoading ->
            if (showLoading) {
                loadingDialog.show(supportFragmentManager, "loading_dialog")
            } else {
                loadingDialog.dismiss()
            }
        }
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
