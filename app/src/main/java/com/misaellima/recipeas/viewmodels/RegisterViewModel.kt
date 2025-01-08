package com.misaellima.recipeas.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.misaellima.recipeas.database.models.User
import com.misaellima.recipeas.repository.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {

    var name = mutableStateOf("")
        private set
    var email = mutableStateOf("")
        private set
    var password = mutableStateOf("")
        private set
    var confirmPassword = mutableStateOf("")
        private set

    // Validation errors
    var usernameError = mutableStateOf<String?>(null)
        private set
    var emailError = mutableStateOf<String?>(null)
        private set
    var passwordError = mutableStateOf<String?>(null)
        private set
    var confirmPasswordError = mutableStateOf<String?>(null)
        private set

    // Update field values
    fun onUsernameChanged(value: String) {
        name.value = value
    }

    fun onEmailChanged(value: String) {
        email.value = value
    }

    fun onPasswordChanged(value: String) {
        password.value = value
    }

    fun onConfirmPasswordChanged(value: String) {
        confirmPassword.value = value
    }

    // Perform validation
    fun validateForm(): Boolean {
        var isValid = true

        if (name.value.isBlank()) {
            usernameError.value = "Username cannot be empty"
            isValid = false
        } else {
            usernameError.value = null
        }

        if (email.value.isBlank() || !email.value.contains("@")) {
            emailError.value = "Invalid email address"
            isValid = false
        } else {
            emailError.value = null
        }

        if (password.value.length < 6) {
            passwordError.value = "Password must be at least 6 characters"
            isValid = false
        } else {
            passwordError.value = null
        }

        if (confirmPassword.value != password.value) {
            confirmPasswordError.value = "Passwords do not match"
            isValid = false
        } else {
            confirmPasswordError.value = null
        }

        return isValid
    }

    fun registerUser(): Boolean {


        val user = User(
            name = name.value,
            email = email.value,
            password = password.value,
        )
        if (!validateForm()) {
            return false
        }
        addUser(user)
        return true
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            try {
                userRepository.addUser(user)
            } catch (e: Exception) {
                throw IllegalArgumentException("Error registering user: ${e.message}")
            }
        }

    }
}

class RegisterViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

