package com.route.chatappc37.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.route.chatappc37.Constants
import com.route.chatappc37.R
import com.route.chatappc37.databinding.ActivityHomeBinding
import com.route.chatappc37.model.Room
import com.route.chatappc37.ui.addRoom.AddRoomActivity
import com.route.chatappc37.ui.base.BaseActivity
import com.route.chatappc37.ui.chat.ChatActivity

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), Navigator,
    OnRoomClickedListener {
    lateinit var adapter: RoomsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.viewModel = viewModel
        viewModel.navigator = this
        adapter = RoomsAdapter(null)
        adapter.onRoomClickedListener = this
        viewDataBinding.roomsRecylcerView.adapter = adapter
        viewModel.getUserFromFireStore()
        viewModel.roomsList.observe(this) { rooms ->
            adapter.updateData(rooms)
        }
    }


    override fun onStart() {
        super.onStart()
        viewModel.getRoomsFromFirestore()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun navigateToAddRoomActivity() {
        val intent = Intent(this@HomeActivity, AddRoomActivity::class.java)
        startActivity(intent)
    }

    override fun onRoomClicked(room: Room, position: Int) {
        val intent = Intent(this@HomeActivity, ChatActivity::class.java)
            .apply {
                putExtra(Constants.ROOM_EXTRA, room)
                return@apply
            }
        startActivity(intent)
    }

}
