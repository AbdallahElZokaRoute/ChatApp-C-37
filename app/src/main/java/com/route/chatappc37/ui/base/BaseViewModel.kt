package com.route.chatappc37.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<NAVIGATOR> : ViewModel() {
    val messageLiveData: MutableLiveData<String> = MutableLiveData()
    val showProgressBar: MutableLiveData<Boolean> = MutableLiveData(false)
    var navigator: NAVIGATOR? = null
}