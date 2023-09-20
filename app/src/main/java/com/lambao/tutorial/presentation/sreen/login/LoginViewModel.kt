package com.lambao.tutorial.presentation.sreen.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _username = mutableStateOf<String>("")
    val username: State<String> get() = _username

    private val _onJoinChat by lazy { MutableSharedFlow<String>() }
    val onJoinChat get() = _onJoinChat.asSharedFlow()

    fun onUsernameChange(username: String) {
        _username.value = username
    }

    fun onJoinClick() {
        viewModelScope.launch {
            if (_username.value.isNotEmpty()) {
                _onJoinChat.emit(_username.value)
            }
        }
    }
}