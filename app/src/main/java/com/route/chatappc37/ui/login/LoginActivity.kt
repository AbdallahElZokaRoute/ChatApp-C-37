package com.route.chatappc37.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.FirebaseApp
import com.route.chatappc37.R
import com.route.chatappc37.databinding.ActivityLoginBinding
import com.route.chatappc37.ui.base.BaseActivity
import com.route.chatappc37.ui.home.HomeActivity
import com.route.chatappc37.ui.register.RegisterActivity

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), Navigator {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        viewDataBinding.viewModel = this@LoginActivity.viewModel

        viewModel.emailError.observe(this) {
            viewDataBinding.emailLayout.error = it
        }
        viewModel.passwordError.observe(this) {
            viewDataBinding.passwordLayout.error = it

        }
        viewDataBinding.createAccountText.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)

        }
        viewModel.messageLiveData.observe(this) {
            showDialogMessage(it, "OK") { dialog, which -> dialog.dismiss() }

        }
        viewModel.navigator = this
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}