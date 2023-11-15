package com.davichois.lukeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(

): ViewModel() {

    private val _isTalk = MutableLiveData<Boolean>()
    val isTalk: LiveData<Boolean> = _isTalk

    fun isTalkAvatar(value: Boolean) {
        viewModelScope.launch {
            _isTalk.postValue(value)
        }
    }

}