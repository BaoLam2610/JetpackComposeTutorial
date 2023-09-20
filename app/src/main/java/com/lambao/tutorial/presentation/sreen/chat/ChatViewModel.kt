package com.lambao.tutorial.presentation.sreen.chat

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lambao.tutorial.common.Resource
import com.lambao.tutorial.data.remote.service.ChatSocketService
import com.lambao.tutorial.data.remote.service.MessageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageService: MessageService,
    private val chatSocketService: ChatSocketService,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _messageText = mutableStateOf<String>("")
    val messageText: State<String> get() = _messageText

    private val _state = mutableStateOf<ChatState>(ChatState())
    val state: State<ChatState> get() = _state

    private val _toastEvent by lazy { MutableSharedFlow<String>() }
    val toastEvent get() = _toastEvent.asSharedFlow()

    fun connectToChat() {
        getAllMessages()
        savedStateHandle.get<String>("username")?.let { username ->
            viewModelScope.launch {
                val result = chatSocketService.initSession(username)
                when (result) {
                    is Resource.Success -> {
                        chatSocketService.observeMessages()
                            .onEach { message ->
                                val newList = state.value.messages.toMutableList().apply {
                                    add(0, message)
                                }
                                _state.value = state.value.copy(messages = newList)

                            }.launchIn(viewModelScope)
                    }

                    is Resource.Error -> {
                        _toastEvent.emit(result.message ?: "Error")
                    }

                    else -> Unit
                }
            }
        }
    }

    fun onMessageChange(message: String) {
        _messageText.value = message
    }

    fun disconnect() {
        viewModelScope.launch {
            chatSocketService.closeSession()
        }
    }

    fun getAllMessages() {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            val result = messageService.getAllMessages()
            _state.value = state.value.copy(
                messages = result,
                isLoading = false
            )

        }
    }

    fun sendMessage() {
        viewModelScope.launch {
            if (_messageText.value.isBlank()) return@launch
            chatSocketService.sendMessage(_messageText.value)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disconnect()
    }
}