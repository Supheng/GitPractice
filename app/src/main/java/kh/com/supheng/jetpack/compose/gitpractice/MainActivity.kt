package kh.com.supheng.jetpack.compose.gitpractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.Navigation
import kh.com.supheng.jetpack.compose.gitpractice.BackendLR.Repository3.AuthRepository
import kh.com.supheng.jetpack.compose.gitpractice.BackendLR.Screen1.AuthScreen
import kh.com.supheng.jetpack.compose.gitpractice.BackendLR.ViewModel2.AuthViewModel
import kh.com.supheng.jetpack.compose.gitpractice.WithFirebase.AppNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppNavigation()
//            val factory = object : ViewModelProvider.Factory {
//                @Suppress("UNCHECKED_CAST")
//                override fun <T : ViewModel> create(
//                    modelClass: Class<T>
//                ): T {
//                    if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
//                        return AuthViewModel(
//                            AuthRepository()
//                        ) as T
//                    }
//
//                    throw IllegalArgumentException(
//                        "Unknown ViewModel class"
//                    )
//                }
//            }
//
//            val authViewModel: AuthViewModel = viewModel(
//                factory = factory
//            )
//
//            AuthScreen(viewModel = authViewModel)
        }
    }
}