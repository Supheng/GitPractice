package kh.com.supheng.jetpack.compose.gitpractice.TestRetrofit

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BusRepository {

    fun getBuses(): Flow<List<Bus>> = flow {

        try {

            val response =
                InstanceRetrofit.busApi.getBus()

            if (response.isSuccessful) {

                emit(
                    response.body() ?: emptyList()
                )

            } else {

                emit(emptyList())
            }

        } catch (e: Exception) {

            emit(emptyList())
        }
    }
}
