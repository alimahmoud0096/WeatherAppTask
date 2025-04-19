package com.alihafez.search.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alihafez.core.domain.model.WeatherModel
import com.alihafez.core.domain.onError
import com.alihafez.core.domain.onSuccess
import com.alihafez.core.presentation.toUiText
import com.alihafez.search.domain.repo.SearchRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

/**
 * created by ِAli Mahmoud Abdelhafez on 18/4/25
 **/
@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepo: SearchRepo) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> =
        _uiState
            .onStart { getCachedList() }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SearchUiState())

    init {
        observeOnCityNameChanged()
    }

    private var searchJob: Job? = null
    private var searchResult: WeatherModel? = null

    private fun getWeatherByCityName(cityName: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            searchRepo.getWeatherByCityName(cityName)
                .onSuccess { result ->
                    searchResult = result
                    _uiState.update {
                        result.asSearchUiState().copy(cityName = uiState.value.cityName)
                    }
                }
                .onError { error ->
                    _uiState.update { it.copy(hasError = error.toUiText()) }
                }
        }
    }

    fun onCityNameChange(cityName: String) {
        Log.d("TAG", "onCityNameChange:${cityName} ")
        _uiState.update { it.copy(cityName = cityName) }
    }



    fun onSearchIconClick() {
        if (searchResult != null && searchResult?.name == uiState.value.cityName) {
            _uiState.update {
                searchResult!!.asSearchUiState().copy(cityName = uiState.value.cityName)
            }
            return
        }
        getWeatherByCityName(uiState.value.cityName)
    }

    fun onSavedLocationClick() {
        onDismiss()
        viewModelScope.launch {
            searchResult?.let { searchRepo.saveLocationToDatabase(it) }
        }
    }

    fun onDismiss() {
        _uiState.update { it.copy(weatherUi = null) }
    }

    @OptIn(FlowPreview::class)
    private fun observeOnCityNameChanged() {
        uiState.map { it.cityName }
            .debounce(DEBOUNCE_TIME_MS)
            .distinctUntilChanged()
            .flowOn(Dispatchers.IO)
            .onEach {
                Log.d("TAG", "observeOnCityNameChanged:${it} ")

                if (it.isNotEmpty()) {
                    getWeatherByCityName(it)
                }
            }.launchIn(viewModelScope)
    }

    private fun getCachedList() {
        viewModelScope.launch {
            searchRepo.getCashedList().collect { result ->
                _uiState.update { it.copy(savedLocations = result.map { it.asSavedLocations() }) }
            }
        }
    }

    companion object {
        private const val DEBOUNCE_TIME_MS = 1000L
    }
}