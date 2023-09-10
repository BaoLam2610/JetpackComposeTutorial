package com.lambao.tutorial.presentation.screen.coin.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lambao.tutorial.common.Resource
import com.lambao.tutorial.domain.model.Coin
import com.lambao.tutorial.domain.use_case.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> get() = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase.invoke().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = CoinListState(coins = it.data ?: emptyList())
                }

                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = CoinListState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class CoinListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val coins: List<Coin> = emptyList()
)