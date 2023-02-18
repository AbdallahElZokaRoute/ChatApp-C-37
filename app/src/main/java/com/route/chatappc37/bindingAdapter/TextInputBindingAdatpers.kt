package com.route.chatappc37.bindingAdapter

import android.util.Log
import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("app:setTextError")
fun EditText.setTextError(error: String?) {
    Log.e("TAG", "CALLED")
    this.error = error
}