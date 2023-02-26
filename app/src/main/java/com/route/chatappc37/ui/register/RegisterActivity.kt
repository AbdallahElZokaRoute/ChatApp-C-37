package com.route.chatappc37.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.FirebaseApp
import com.route.chatappc37.R
import com.route.chatappc37.databinding.ActivityRegisterBinding
import com.route.chatappc37.ui.home.HomeActivity
import com.route.chatappc37.ui.base.BaseActivity

class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        viewDataBinding.viewModel = this@RegisterActivity.viewModel
        viewModel.emailError.observe(this) {
            viewDataBinding.emailLayout.error = it
        }
        viewModel.firstNameError.observe(this) {
            viewDataBinding.firstNameLayout.error = it
        }
        viewModel.lastNameError.observe(this) {
            viewDataBinding.lastNameLayout.error = it
        }
        viewModel.passwordError.observe(this) {
            viewDataBinding.passwordLayout.error = it
        }
        viewModel.navigator = this
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initViewModel(): RegisterViewModel {
        return ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}