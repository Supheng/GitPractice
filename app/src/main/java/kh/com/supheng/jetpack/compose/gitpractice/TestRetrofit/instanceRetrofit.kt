package kh.com.supheng.jetpack.compose.gitpractice.TestRetrofit

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InstanceRetrofit{
    val busApi : BusApi by lazy {
        Retrofit.Builder()
            .baseUrl("http/10.0.2.2:8080/")
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(BusApi:: class.java
            )
    }
}