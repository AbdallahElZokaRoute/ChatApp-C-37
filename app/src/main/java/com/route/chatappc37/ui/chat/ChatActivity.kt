package com.route.chatappc37.ui.chat

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.route.chatappc37.Constants
import com.route.chatappc37.R
import com.route.chatappc37.databinding.ActivityChatBinding
import com.route.chatappc37.ui.base.BaseActivity

class ChatActivity : BaseActivity<ActivityChatBinding, ChatViewModel>() {
    val adapter = MessagesAdapter(null)
    lateinit var layoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.viewModel = viewModel
        viewDataBinding.messagesRecyclerView.adapter = adapter
        layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        viewDataBinding.messagesRecyclerView.layoutManager = layoutManager
        viewModel.room = intent.getParcelableExtra(Constants.ROOM_EXTRA)
        viewModel.listenForChanges()
        viewModel.getMessagesFromFireStore()
        viewModel.messageReceivedLiveData.observe(this) {
            adapter.receivedNewMessage(it)
        }
        viewModel.isScrollToBottom.observe(this) {
            if (it) {
                Log.e("TAG", "onCreate: isScroll To bottom")
                layoutManager.smoothScrollToPosition(
                    viewDataBinding.messagesRecyclerView,
                    RecyclerView.State(), adapter.itemCount
                )
            }

        }
        viewModel.messagesLiveData.observe(this) {
            adapter.updateData(it)
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_chat


    override fun initViewModel(): ChatViewModel =
        ViewModelProvider(this).get(ChatViewModel::class.java)
}