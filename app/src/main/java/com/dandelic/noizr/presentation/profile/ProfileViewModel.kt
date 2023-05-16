package com.dandelic.noizr.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.dandelic.noizr.domain.model.Response.Loading
import com.dandelic.noizr.domain.model.Response.Success
import com.dandelic.noizr.domain.repository.AuthRepository
import com.dandelic.noizr.domain.repository.ReloadUserResponse
import com.dandelic.noizr.domain.repository.RevokeAccessResponse
import com.dandelic.noizr.domain.repository.UpdatePasswordResponse
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {
    var revokeAccessResponse by mutableStateOf<RevokeAccessResponse>(Success(false))
        private set
    var reloadUserResponse by mutableStateOf<ReloadUserResponse>(Success(false))
        private set

    var updatePasswordResponse by mutableStateOf<UpdatePasswordResponse>(Success(false))
        private set

    fun reloadUser() = viewModelScope.launch {
        reloadUserResponse = Loading
        reloadUserResponse = repo.reloadFirebaseUser()
    }

    val isEmailVerified get() = repo.currentUser?.isEmailVerified ?: false

    fun signOut() = repo.signOut()

    fun getUserEmail() = repo.currentUser?.email

    fun revokeAccess() = viewModelScope.launch {
        revokeAccessResponse = Loading
        revokeAccessResponse = repo.revokeAccess()
    }

    fun updateUserPassword(password: String) = viewModelScope.launch {
        updatePasswordResponse = Loading
        updatePasswordResponse = repo.updateUserPassword(password)
    }
}