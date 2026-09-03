package kh.com.supheng.jetpack.compose.gitpractice.TestRetrofit

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun Screen(){
    var buses by remember {
        mutableStateOf<List<Bus>>(emptyList())
    }

    LaunchedEffect(Unit) {

        try {

            val response =
                InstanceRetrofit.busApi.getBus()

            if (response.isSuccessful) {

                buses = response.body() ?: emptyList()
            }

        } catch (e : Exception){
            print(e.message)
        }
    }

    LazyColumn {

        items(buses) { bus ->

            Text(
                text = "${bus.name} - $${bus.price}"
            )
        }
    }
}
@Composable
fun BusScreen(
    viewModel: BusVM
) {
    val buses by viewModel.bus.collectAsStateWithLifecycle()
// only one line replace all this code
//    val repository = remember {
//        BusRepository()
//    }
//
//    var buses by remember {
//        mutableStateOf<List<Bus>>(emptyList())
//    }
//
//    LaunchedEffect(Unit) {
//
//        repository.getBuses().collect { result ->
//
//            buses = result
//        }
//    }

    LazyColumn {

        items(buses) { bus ->

            Text(
                text = "${bus.name} - $${bus.price}"
            )
        }
    }
}