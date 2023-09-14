package com.lambao.tutorial.presentation.screen.company_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lambao.tutorial.common.Resource
import com.lambao.tutorial.domain.model.CompanyInfo
import com.lambao.tutorial.domain.model.IntradayInfo
import com.lambao.tutorial.domain.repository.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: StockRepository
) : ViewModel() {

    var state by mutableStateOf(CompanyInfoState())

    init {
        viewModelScope.launch {
            val symbol = savedStateHandle.get<String>("symbol") ?: return@launch
            state = state.copy(isLoading = true)
            val companyInfoResult = async {
                repository.getCompanyInfo(symbol)
            }
            val intradayInfoResult = async {
                repository.getIntradayInfo(symbol)
            }
            when (val result = companyInfoResult.await()) {
                is Resource.Success -> state = state.copy(company = result.data, isLoading = false)
                is Resource.Error -> state = state.copy(isLoading = false, error = result.message)
                is Resource.Loading -> Unit
            }
            when (val result = intradayInfoResult.await()) {
                is Resource.Success -> state =
                    state.copy(stockInfos = result.data ?: emptyList(), isLoading = false)

                is Resource.Error -> state = state.copy(isLoading = false, error = result.message)
                is Resource.Loading -> Unit
            }
        }
    }
}

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)