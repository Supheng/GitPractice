package kh.com.supheng.jetpack.compose.gitpractice.WithFirebase.Auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.supheng.jetpack.compose.gitpractice.WithFirebase.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel(){
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    fun register(email: String, password: String) {
        authenticate {
            repository.register(email, password)
        }
    }

    fun login(email: String, password: String) {
        authenticate {
            repository.login(email, password)
        }
    }

    private fun authenticate(
        action: suspend () -> Result<Unit>
    ) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, errorMessage = null)
            }

            val result = action()

            _uiState.update {
                result.fold(
                    onSuccess = {success ->
                        it.copy(
                            isLoading = false,
                            isLoggedIn = true,
                            errorMessage = null
                        )
                    },
                    onFailure = { error ->
                        it.copy(
                            isLoading = false,
                            errorMessage = error.message
                                ?: "Authentication failed"
                        )
                    }
                )
            }
        }
    }
    fun clearError() {
        _uiState.update {
            it.copy(errorMessage = null)
        }
    }

    fun signOut() {
        repository.signOut()
        _uiState.update {
            AuthUiState()
        }
    }
}
