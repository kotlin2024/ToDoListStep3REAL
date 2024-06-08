package org.sparta.todoliststep3.domain.user.dto

data class UserSignupRequest(
    val userEmail: String,
    val userPassword: String,
    val userName: String,
    val role:String
)
