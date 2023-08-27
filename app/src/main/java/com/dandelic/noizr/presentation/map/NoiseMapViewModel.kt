package com.dandelic.noizr.presentation.map

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dandelic.noizr.domain.model.Response
import com.dandelic.noizr.domain.repository.AuthRepository
import com.dandelic.noizr.domain.repository.GetMeasuringsResponse
import com.dandelic.noizr.domain.repository.MeasuringRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoiseMapViewModel @Inject constructor(
    private val measuringRepo: MeasuringRepository,
    private val authRepository: AuthRepository,
): ViewModel() {

    var getMeasuringsResponse by mutableStateOf<GetMeasuringsResponse>(
        Response.Success(
        mutableListOf()
    ))

    fun getMeasurings() = viewModelScope.launch {
        getMeasuringsResponse = Response.Loading
        getMeasuringsResponse = measuringRepo.getMeasurings(authRepository.currentUser?.uid)
    }

}