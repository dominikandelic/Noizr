package com.dandelic.noizr.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.dandelic.noizr.data.repository.AuthRepositoryImpl
import com.dandelic.noizr.data.repository.MeasuringRepositoryImpl
import com.dandelic.noizr.domain.repository.AuthRepository
import com.dandelic.noizr.domain.repository.MeasuringRepository
import com.google.firebase.firestore.ktx.firestore

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {
    @Provides
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(
        auth = Firebase.auth
    )

    @Provides
    fun provideFirebaseFirestore(): MeasuringRepository = MeasuringRepositoryImpl(
        db = Firebase.firestore
    )
}