package kh.com.supheng.jetpack.compose.gitpractice.TestRetrofit

import okhttp3.Response
import retrofit2.http.GET

interface BusApi {
    @GET("api/Buses")
    suspend fun getBus() : retrofit2.Response<List<Bus>>
}