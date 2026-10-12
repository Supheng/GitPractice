package kh.com.supheng.jetpack.compose.gitpractice.BackendLR.Retrofit

import kh.com.supheng.jetpack.compose.gitpractice.BackendLR.Model.AuthResponse
import kh.com.supheng.jetpack.compose.gitpractice.BackendLR.Model.LoginRequest
import kh.com.supheng.jetpack.compose.gitpractice.BackendLR.Model.RegisterRequest
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<AuthResponse>

    @POST("api/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<AuthResponse>
}