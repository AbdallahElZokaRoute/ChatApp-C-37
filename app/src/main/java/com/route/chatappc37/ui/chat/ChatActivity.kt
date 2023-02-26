package com.route.chatappc37.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.route.chatappc37.R
import com.route.chatappc37.databinding.ActivityChatBinding
import com.route.chatappc37.ui.base.BaseActivity

class ChatActivity : BaseActivity<ActivityChatBinding, ChatViewModel>() {
    val adapter = MessagesAdapter(null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.viewModel = viewModel


    }

    override fun getLayoutId(): Int = R.layout.activity_chat


    override fun initViewModel(): ChatViewModel =
        ViewModelProvider(this).get(ChatViewModel::class.java)
}