package com.lambao.tutorial.presentation.screen.coin.list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lambao.tutorial.common.JobRegistry
import com.lambao.tutorial.common.Resource
import com.lambao.tutorial.domain.model.Coin
import com.lambao.tutorial.domain.use_case.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val jobRegistry: JobRegistry
) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> get() = _state

    init {
//        getCoins()
    }

    fun getCoins() {
        val job = getCoinsUseCase.invoke().onEach {
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
        val index = jobRegistry.addJob(job)
        Log.d("lamnb", "getCoins count: $index")
    }

    fun cancelJob() {
        jobRegistry.cancelAll()
    }
}

data class CoinListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val coins: List<Coin> = emptyList()
)