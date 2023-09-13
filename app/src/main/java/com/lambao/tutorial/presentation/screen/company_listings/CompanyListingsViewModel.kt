package com.lambao.tutorial.presentation.screen.company_listings


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lambao.tutorial.common.Resource
import com.lambao.tutorial.domain.model.CompanyListing
import com.lambao.tutorial.domain.repository.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyListingsViewModel @Inject constructor(
    private val repository: StockRepository
) : ViewModel() {

    var state by mutableStateOf(CompanyListingsState())

    private var searchJob: Job? = null

    fun onEvent(event: CompanyListingsEvent) {
        when (event) {
            is CompanyListingsEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500)
                    getCompanyListings()
                }
            }
            is CompanyListingsEvent.Refresh -> {
                getCompanyListings(fetchFromRemote = true)
            }
        }
    }

    fun getCompanyListings(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCompanyListings(fetchFromRemote, query)
                .collect {
                    when (it) {
                        is Resource.Success -> {
                            it.data?.let { data ->
                                state = state.copy(
                                    companies = data,
                                )
                            }
                        }
                        is Resource.Loading -> state = state.copy(isLoading = it.isLoading)
                        is Resource.Error -> Unit
                    }
                }
        }

    }
}

data class CompanyListingsState(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)

