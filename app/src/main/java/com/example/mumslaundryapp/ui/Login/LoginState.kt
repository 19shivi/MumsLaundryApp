package com.example.mumslaundryapp.ui.Login

data class LoginState(
val isLoading: Boolean = false,
val isEmailVerified: Boolean = false,
val isLoginSuccess: Boolean = false,
val isSignupSuccess: Boolean = false,
val error: String = "",

)
