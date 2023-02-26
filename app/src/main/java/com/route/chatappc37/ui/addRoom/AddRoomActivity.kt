package com.route.chatappc37.ui.addRoom

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.route.chatappc37.R
import com.route.chatappc37.databinding.ActivityAddRoomBinding
import com.route.chatappc37.model.Category
import com.route.chatappc37.ui.base.BaseActivity


class AddRoomActivity : BaseActivity<ActivityAddRoomBinding, AddRoomViewModel>() {
    lateinit var spinnerAdapter: CategorySpinnerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.viewModel = viewModel
        spinnerAdapter = CategorySpinnerAdapter(viewModel.categoriesList)

        viewDataBinding.categorySpinner.adapter = spinnerAdapter
        viewDataBinding.categorySpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.selectedCategory = viewModel.categoriesList[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        viewModel.finishActivity.observe(this) {
            if (it) {
                finish()
            }

        }
    }

    override fun getLayoutId(): Int = R.layout.activity_add_room

    override fun initViewModel(): AddRoomViewModel =
        ViewModelProvider(this).get(AddRoomViewModel::class.java)

}