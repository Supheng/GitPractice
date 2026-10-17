package kh.com.supheng.jetpack.compose.gitpractice.BackendLR.Repository3

import kh.com.supheng.jetpack.compose.gitpractice.BackendLR.Model.AuthResponse
import kh.com.supheng.jetpack.compose.gitpractice.BackendLR.Model.LoginRequest
import kh.com.supheng.jetpack.compose.gitpractice.BackendLR.Model.RegisterRequest
import kh.com.supheng.jetpack.compose.gitpractice.BackendLR.Retrofit4.RetrofitInstance

class AuthRepository {

    suspend fun register(
        username: String,
        email: String,
        password: String
    ): Result<String> = requestResult {
        RetrofitInstance.api.register(
            RegisterRequest(username, email, password)
        )
    }

        suspend fun login(
            email: String,
            password: String
        ): Result<String> = requestResult {
            RetrofitInstance.api.login(
                LoginRequest(email, password)
            )
        }

    private suspend fun requestResult(
        request: suspend () -> retrofit2.Response<AuthResponse>
    ): Result<String> {
        return try {
            val response = request()

            if (response.isSuccessful) {
                Result.success(
                    response.body()?.message ?: "Request successful"
                )
            } else {
                val errorMessage = try {
                    org.json.JSONObject(
                        response.errorBody()?.string().orEmpty()
                    ).optString("message", "Request failed")
                } catch (_: Exception) {
                    "Request failed: HTTP ${response.code()}"
                }

                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Result.failure(
                Exception(e.message ?: "Unable to connect to the server")
            )
        }
    }
}