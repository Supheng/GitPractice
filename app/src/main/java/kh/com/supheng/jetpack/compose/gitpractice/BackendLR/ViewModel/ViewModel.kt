package kh.com.supheng.jetpack.compose.gitpractice.BackendLR.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.supheng.jetpack.compose.gitpractice.BackendLR.Repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<AuthUiState>(AuthUiState.Idle)

    val uiState = _uiState.asStateFlow()

    fun register(username: String, email: String, password: String) {
        if (username.isBlank() || email.isBlank() || password.length < 6) {
            _uiState.value =
                AuthUiState.Error("Complete all fields. Password needs 6 characters.")
            return
        }

        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading

            repository.register(username, email, password)
                .onSuccess {
                    _uiState.value = AuthUiState.Success(it)
                }
                .onFailure {
                    _uiState.value = AuthUiState.Error(
                        it.message ?: "Registration failed"
                    )
                }
        }
    }

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.value =
                AuthUiState.Error("Email and password are required")
            return
        }

        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading

            repository.login(email, password)
                .onSuccess {
                    _uiState.value = AuthUiState.Success(it)
                }
                .onFailure {
                    _uiState.value = AuthUiState.Error(
                        it.message ?: "Login failed"
                    )
                }
        }
    }

    fun resetState() {
        _uiState.value = AuthUiState.Idle
    }
}