package com.dandelic.noizr.data.repository

import android.util.Log
import com.dandelic.noizr.domain.model.Measuring
import com.dandelic.noizr.domain.repository.MeasuringRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.dandelic.noizr.domain.model.Response.Failure
import com.dandelic.noizr.domain.model.Response.Success
import com.dandelic.noizr.domain.repository.CreateMeasuringResponse
import com.dandelic.noizr.domain.repository.DeleteMeasuringResponse
import com.dandelic.noizr.domain.repository.GetMeasuringsResponse
import com.google.firebase.firestore.DocumentSnapshot
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MeasuringRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : MeasuringRepository {
    override suspend fun getMeasurings(userUid: String?): GetMeasuringsResponse {
        val responses = mutableListOf<Measuring>()
        return try {
            val documents = if(userUid != null) {
                db.collection("measurings")
                    .whereEqualTo("userUid", userUid)
                    .get().await().documents
            } else {
                db.collection("measurings")
                    .get().await().documents
            }
            for (document in documents) {
                document.toObject(Measuring::class.java)?.let { responses.add(it) }
            }
            Success(responses)
        } catch (e: Exception) {
            e.message?.let { Log.d("MEASURING_ERROR", it) }
            Failure(e)
        }
    }

    override suspend fun createMeasuring(measuring: Measuring): CreateMeasuringResponse {
        val data = hashMapOf(
            "createdAt" to measuring.createdAt,
            "geolocation" to measuring.geolocation,
            "noiseLevel" to measuring.noiseLevel,
            "userUid" to measuring.userUid
        )

        return try {
            db.collection("measurings")
                .add(data)
                .await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun deleteMeasuring(documentId: String): DeleteMeasuringResponse {
        return try {
            db.collection("measurings")
                .document(documentId)
                .delete().await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}