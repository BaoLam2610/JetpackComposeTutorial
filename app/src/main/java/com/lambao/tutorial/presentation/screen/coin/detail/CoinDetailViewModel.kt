package com.lambao.tutorial.presentation.screen.coin.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lambao.tutorial.common.Resource
import com.lambao.tutorial.domain.model.CoinDetail
import com.lambao.tutorial.domain.use_case.GetCoinDetailUseCase
import com.lambao.tutorial.presentation.screen.coin.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> get() = _state

    private val navArgs: CoinDetailScreenNavArgs = savedStateHandle.navArgs()
    /* OR
    private val navArgs: CoinDetailScreenNavArgs =
        CoinDetailScreenDestination.argsFrom(savedStateHandle)*/

    init {
        getCoin(navArgs.coinId)
    }

    private fun getCoin(id: String) {
        getCoinDetailUseCase.invoke(id).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = it.data)
                }

                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = CoinDetailState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class CoinDetailState(
    val isLoading: Boolean = false,
    val error: String = "",
    val coin: CoinDetail? = null
)