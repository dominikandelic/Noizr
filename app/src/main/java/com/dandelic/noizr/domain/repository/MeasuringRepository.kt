package com.dandelic.noizr.domain.repository

import com.dandelic.noizr.domain.model.Measuring
import com.dandelic.noizr.domain.model.Response

typealias GetMeasuringsResponse = Response<List<Measuring>>
typealias CreateMeasuringResponse = Response<Boolean>
typealias DeleteMeasuringResponse = Response<Boolean>

interface MeasuringRepository {

    suspend fun getMeasurings(userUid: String?): GetMeasuringsResponse

    suspend fun createMeasuring(measuring: Measuring): CreateMeasuringResponse

    suspend fun deleteMeasuring(documentId: String): DeleteMeasuringResponse
}