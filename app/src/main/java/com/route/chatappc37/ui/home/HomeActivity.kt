package com.route.chatappc37.ui.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.route.chatappc37.R
import com.route.chatappc37.databinding.ActivityHomeBinding
import com.route.chatappc37.ui.base.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

}
