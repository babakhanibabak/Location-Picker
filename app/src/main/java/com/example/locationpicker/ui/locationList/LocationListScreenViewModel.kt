package com.example.locationpicker.ui.locationList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.locationpicker.domain.UseCase.GetLocationsUseCase
import com.example.locationpicker.ui.locationList.mapper.LocationListUiMapper
import com.example.locationpicker.ui.locationList.model.LocationListScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationListScreenViewModel @Inject constructor(
    private val uiMapper: LocationListUiMapper,
    private val UseCase: GetLocationsUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<LocationListScreenState> =
        MutableStateFlow(LocationListScreenState.loading)

    val uiState by lazy {
        loadLocations()
        _uiState.asStateFlow()
    }
    private fun loadLocations() {
        viewModelScope.launch {
            runCatching {
                UseCase.execute()
        }.onFailure{
            _uiState.value = LocationListScreenState.error(it.message.toString())

            }.onSuccess {
                _uiState.value = LocationListScreenState.success(uiMapper.mapToUiModel(it))
            }

    }

}
}