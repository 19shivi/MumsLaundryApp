package com.example.mumslaundryapp.ui.Login

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mumslaundryapp.common.isValidEmail
import com.example.mumslaundryapp.common.matchesString
import com.example.mumslaundryapp.common.showToastMessage
import com.example.mumslaundryapp.ui.home.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val auth: FirebaseAuth): ViewModel() { private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state


    fun signIn(email:String,password:String) {
        if (!isValidEmail(email)) {
            _state.value = LoginState(isLoginSuccess = false, error = "Invalid Email")
            return
        }
        if (password.length < 8) {
            _state.value = LoginState(
                isLoginSuccess = false,
                error = "Password should contain atleast 8 characters"
            )
            return
        }
        _state.value = LoginState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    _state.value = LoginState(isLoading = false)
                    if (task.isSuccessful) {
                        var user = auth.currentUser
                        if (user?.isEmailVerified == true)
                            _state.value = LoginState(isLoginSuccess = true)
                        else {
                            user?.sendEmailVerification()?.addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        auth.signOut()
                                        _state.value = LoginState(
                                            isLoading = false,
                                            isSignupSuccess = true,
                                            error = "Email Not Verified Please Verify to Login"
                                        )

                                    } else {
                                        _state.value = LoginState(
                                            isLoading = false,
                                            isSignupSuccess = false,
                                            error = "Login Failed"
                                        )
                                    }

                                auth.signOut()

                            }
                        }
                        } else {
                            _state.value = LoginState(
                                isLoginSuccess = false,
                                error = "Invalid Login Credentials"
                            )
                        }
                    }
                }
        }


    fun signUp(email:String,password:String,confirmPassword:String,name: String) {
        if(name.isEmpty())
        {
            _state.value=LoginState(isLoginSuccess = false, error = "Name Cannot Be Empty")
            return
        }
        if(!isValidEmail(email) )
        {
            _state.value=LoginState(isLoginSuccess = false, error = "Email is Invalid")
            return
        }
        if(password.isEmpty()|| password.length<8)
        {
            _state.value=LoginState(isLoginSuccess = false, error = "Password should contain atleast 8 characters")
            return
        }
        if(!matchesString(password, confirmPassword))
        {
            _state.value=LoginState(isLoginSuccess = false, error = "Password and Confirm Password did not match")
            return
        }
        _state.value = LoginState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        var nameChange =
                            UserProfileChangeRequest.Builder().setDisplayName(name).build();
                        user?.updateProfile(nameChange)?.addOnCompleteListener {

                            if (it.isSuccessful) {
                               user.sendEmailVerification().addOnCompleteListener {
                                   if (it.isSuccessful) {
                                        auth.signOut()
                                       _state.value = LoginState(isLoading = false, isSignupSuccess = true, error = "Email Verification Link Sent Please Verify it")

                                   } else {
                                       _state.value = LoginState(isLoading = false, isSignupSuccess = false, error = "Signup Failed")
                                   }
                               }

                            } else {
                                _state.value = LoginState(isLoading = false, isSignupSuccess = false, error = "Signup Failed")
                            }
                        }


                    } else {
                        _state.value = LoginState(isLoading = false, isSignupSuccess = false, error = "Signup Failed")
                    }
                }

        }
    }

    fun forgetPassword(email:String)
    {
        if(!isValidEmail(email) )
        {
            _state.value=LoginState(isLoginSuccess = false, error = "Email is Invalid")
            return
        }
        _state.value = LoginState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        _state.value = LoginState(isLoading = false,  error = "Password Reset  Link Sent Please Verify it")

                    } else {
                        _state.value = LoginState(isLoading = false, isSignupSuccess = false, error = "Password Reset Failed")

                    }
                }}}

}
