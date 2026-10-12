package kh.com.supheng.jetpack.compose.gitpractice.BackendLR.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kh.com.supheng.jetpack.compose.gitpractice.BackendLR.ViewModel.AuthUiState
import kh.com.supheng.jetpack.compose.gitpractice.BackendLR.ViewModel.AuthViewModel

@Composable
fun AuthScreen(viewModel: AuthViewModel) {
    var isRegister by remember { mutableStateOf(false) }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(isRegister) {
        viewModel.resetState()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (isRegister) "Create Account" else "Welcome Back",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = if (isRegister)
                "Register to test your backend"
            else
                "Login with your test account"
        )

        Spacer(Modifier.height(28.dp))

        if (isRegister) {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))
        }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                if (isRegister) {
                    viewModel.register(username, email, password)
                } else {
                    viewModel.login(email, password)
                }
            },
            enabled = uiState !is AuthUiState.Loading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (uiState is AuthUiState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text(if (isRegister) "Register" else "Login")
            }
        }

        Spacer(Modifier.height(12.dp))

        TextButton(
            onClick = {
                isRegister = !isRegister
                username = ""
                email = ""
                password = ""
            }
        ) {
            Text(
                if (isRegister)
                    "Already have an account? Login"
                else
                    "Don't have an account? Register"
            )
        }

        Spacer(Modifier.height(16.dp))

        when (val state = uiState) {
            is AuthUiState.Success -> {
                Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            is AuthUiState.Error -> {
                Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.error
                )
            }

            else -> Unit
        }
    }
}