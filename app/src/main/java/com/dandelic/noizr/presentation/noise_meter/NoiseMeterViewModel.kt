package com.dandelic.noizr.presentation.noise_meter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dandelic.noizr.domain.model.Measuring
import com.dandelic.noizr.domain.model.Response
import com.dandelic.noizr.domain.repository.AuthRepository
import com.dandelic.noizr.domain.repository.CreateMeasuringResponse
import com.dandelic.noizr.domain.repository.MeasuringRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoiseMeterViewModel @Inject constructor(
    private val measuringRepo: MeasuringRepository,
    private val authRepository: AuthRepository,
): ViewModel() {

    var createMeasuringResponse by mutableStateOf<CreateMeasuringResponse>(Response.Success(false))

    fun getUserUid() = authRepository.currentUser?.uid

    fun createMeasuring(measuring: Measuring) = viewModelScope.launch {
        createMeasuringResponse = Response.Loading
        createMeasuringResponse = measuringRepo.createMeasuring(measuring)
    }
}