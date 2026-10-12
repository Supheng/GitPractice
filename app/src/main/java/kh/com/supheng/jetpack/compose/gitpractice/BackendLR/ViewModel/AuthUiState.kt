package kh.com.supheng.jetpack.compose.gitpractice.BackendLR.ViewModel

sealed interface AuthUiState {
    data object Idle : AuthUiState
    data object Loading : AuthUiState
    data class Success(val message: String) : AuthUiState
    data class Error(val message: String) : AuthUiState
}