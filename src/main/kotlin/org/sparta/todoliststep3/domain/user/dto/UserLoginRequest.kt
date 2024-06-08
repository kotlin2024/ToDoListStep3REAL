package org.sparta.todoliststep3.domain.user.dto

data class UserLoginRequest(
    val userEmail: String,
    val userPassword: String,
)
