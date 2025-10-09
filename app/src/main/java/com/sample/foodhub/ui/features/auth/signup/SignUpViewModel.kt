package com.sample.foodhub.ui.features.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.foodhub.data.FoodApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(val foodApi: FoodApi) : ViewModel() {

    private val _uiState = MutableStateFlow<SignUpUiEvent>(SignUpUiEvent.Idle)
    val uiState = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<SignUpUiNavigationEvent?>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()
    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setName(name: String) {
        _name.value = name
    }

    fun onSignUpClick() {
        viewModelScope.launch {
            _uiState.value = SignUpUiEvent.Loading
            try {
                kotlinx.coroutines.delay(3000)
                val response = foodApi.signUp(
                    com.sample.foodhub.data.models.SignUpRequest(
                        name = name.value,
                        email = email.value,
                        password = password.value
                    )
                )
                // On success
                _uiState.value = SignUpUiEvent.Success
                if (response.token.isNotEmpty()) {
                    _uiState.value = SignUpUiEvent.Success
                    _navigationEvent.emit(SignUpUiNavigationEvent.NavigateToHomeScreen)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = SignUpUiEvent.Error(e.message ?: "Unknown error")
            }
        }
    }

    //Two events for a given screen: 1. UI State 2. Navigation state (side effects)
    sealed class SignUpUiEvent {
        object Idle : SignUpUiEvent()
        object Loading : SignUpUiEvent()
        data class Error(val message: String) : SignUpUiEvent()
        object Success : SignUpUiEvent()
    }

    sealed class SignUpUiNavigationEvent {
        object NavigateToHomeScreen : SignUpUiNavigationEvent()
        object NavigateToLoginScreen : SignUpUiNavigationEvent()
    }
}