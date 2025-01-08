package com.misaellima.recipeas.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.misaellima.recipeas.repository.UserRepository
import com.misaellima.recipeas.viewmodels.RegisterViewModel
import com.misaellima.recipeas.viewmodels.RegisterViewModelFactory


@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    userRepository: UserRepository,
    onRegisterSuccess: () -> Unit
) {
    val viewModel: RegisterViewModel = viewModel(
        factory = RegisterViewModelFactory(userRepository)
    )

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Create your account",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 20.dp)
        )
        RegisterForm(
            viewModel = viewModel,
            onRegisterClick = {
                if (viewModel.registerUser()) {
                    onRegisterSuccess()
                }
            }
        )

        Row (
            modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp),
        ) {
            Text(
                text = "Already have an account?",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        Row {
            Text(
                text = "Login",
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold,
            )
        }


    }
}

@Composable
fun RegisterForm(
    viewModel: RegisterViewModel,
    onRegisterClick: () -> Unit
) {

    Column {

        //Name field
        OutlinedTextField(
            value = viewModel.name.value,
            onValueChange = {
            viewModel.onUsernameChanged(it)
        },
            isError = viewModel.usernameError.value != null,
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "name")
            },

            label = {
                Text(text = "your name")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp, 0.dp, 0.dp),
            shape = RoundedCornerShape(32.dp)
        )
        if (viewModel.usernameError.value != null) {
            Text(
                text = viewModel.usernameError.value!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall
            )
        }

        // Email field
        OutlinedTextField(
            value = viewModel.email.value,
            onValueChange = {
            viewModel.onEmailChanged(it)
        },
            isError = viewModel.emailError.value != null,
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "email")
            },

            label = {
                Text(text = "email")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp, 0.dp, 0.dp),
            shape = RoundedCornerShape(32.dp)
        )

        if (viewModel.emailError.value != null) {
            Text(
                text = viewModel.emailError.value!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall
            )
        }

        // Password input field
        OutlinedTextField(
            value = viewModel.password.value,
            onValueChange = { viewModel.onPasswordChanged(it) },
            isError = viewModel.passwordError.value != null,
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "password")
            },
            label = {
                Text(text = "password")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp, 0.dp, 0.dp),
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(32.dp)
        )

        if (viewModel.passwordError.value != null) {
            Text(
                text = viewModel.passwordError.value!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall
            )
        }
        //Confirm password input field
        OutlinedTextField(
            value = viewModel.confirmPassword.value,
            onValueChange = { viewModel.onConfirmPasswordChanged(it) },
            isError = viewModel.confirmPasswordError.value != null,
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "confirm password")
            },
            label = {
                Text(text = "confirm password")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp, 0.dp, 0.dp),
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(32.dp)
        )
        if (viewModel.confirmPasswordError.value != null) {
            Text(
                text = viewModel.confirmPasswordError.value!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall
            )
        }

        //Register button
        Button(
            onClick = { onRegisterClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp, 0.dp, 0.dp)
                .height(50.dp)
        ) {
            Text(
                text = "Register",
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "By clicking on register you agree to our terms and conditions",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
                textAlign = TextAlign.Center
            )
        }


    }
}

