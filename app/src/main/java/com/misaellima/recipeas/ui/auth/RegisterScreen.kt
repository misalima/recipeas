package com.misaellima.recipeas.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onRegisterClick: () -> Unit
) {
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
            fontWeight = FontWeight.Bold
        )
        RegisterForm() {  }

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
    onRegisterClick: () -> Unit
) {

    val name = remember {
        mutableStateOf("")
    }
    val email = remember {
        mutableStateOf("")
    }
    val userPassword = remember {
        mutableStateOf("")
    }
    val confirmPassword = remember {
        mutableStateOf("")
    }

    Column {

        //Name field
        OutlinedTextField(value = name.value, onValueChange = {
            name.value = it
        },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "name")
            },

            label = {
                Text(text = "your name")
            },
            modifier = Modifier.fillMaxWidth().padding(0.dp, 10.dp, 0.dp, 0.dp)
        )

        // Email field
        OutlinedTextField(value = email.value, onValueChange = {
            email.value = it
        },
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "email")
            },

            label = {
                Text(text = "email")
            },
            modifier = Modifier.fillMaxWidth().padding(0.dp, 10.dp, 0.dp, 0.dp)
        )

        // Password input field
        OutlinedTextField(value = userPassword.value, onValueChange = {
            userPassword.value = it
        },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "password")
            },
            label = {
                Text(text = "password")
            },
            modifier = Modifier.fillMaxWidth().padding(0.dp, 10.dp, 0.dp, 0.dp),
            visualTransformation = PasswordVisualTransformation()
        )
        //Confirm password input field
        OutlinedTextField(value = confirmPassword.value, onValueChange = {
            confirmPassword.value = it
        },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "confirm password")
            },
            label = {
                Text(text = "confirm password")
            },
            modifier = Modifier.fillMaxWidth().padding(0.dp, 10.dp, 0.dp, 0.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        //Register button
        Button(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth().padding(0.dp, 20.dp, 0.dp, 0.dp).height(50.dp)
        ) {
            Text(
                text = "Register",
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        }
        Row (
            modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp),
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

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen(onRegisterClick = {})
}