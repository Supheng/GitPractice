package kh.com.supheng.jetpack.compose.gitpractice.TestRetrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BusVM : ViewModel() {
    private  val repository = BusRepository()
    private  val _bus = MutableStateFlow<List<Bus>>(emptyList())
    val bus : StateFlow<List<Bus>> = _bus

    fun loadBus(){
        viewModelScope.launch {
            repository.getBuses().collect {result ->
                _bus.value = result
            }
        }
    }
}