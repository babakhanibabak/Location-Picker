package com.example.locationpicker.ui.locationlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.locationpicker.domain.usecase.GetLocationsUseCase
import com.example.locationpicker.ui.locationlist.mapper.LocationListUiMapper
import com.example.locationpicker.ui.locationlist.model.LocationListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationListScreenViewModel @Inject constructor(
    private val uiMapper: LocationListUiMapper,
    private val useCase: GetLocationsUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<LocationListScreenState> =
        MutableStateFlow(LocationListScreenState.Loading)

    val uiState by lazy {
        loadLocations()
        _uiState.asStateFlow()
    }

    private fun loadLocations() {
        viewModelScope.launch {
            runCatching {
                useCase.execute()
            }.onFailure {
                _uiState.value = LocationListScreenState.Error(it.message.toString())

            }.onSuccess {
                _uiState.value = LocationListScreenState.Success(uiMapper.mapToUiModel(it))
            }
        }
    }
}