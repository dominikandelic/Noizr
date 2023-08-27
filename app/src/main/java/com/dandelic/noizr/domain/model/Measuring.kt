package com.dandelic.noizr.domain.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.GeoPoint

data class Measuring(
    @DocumentId val documentId: String = "",
    var createdAt: Timestamp? = null,
    var geolocation: GeoPoint? = null,
    var noiseLevel: Int = 0,
    var userUid: String = ""
)



