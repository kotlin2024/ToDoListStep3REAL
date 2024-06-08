package org.sparta.todoliststep3.domain.user.controller

import org.sparta.todoliststep3.domain.user.dto.UserLoginRequest
import org.sparta.todoliststep3.domain.user.dto.UserResponse
import org.sparta.todoliststep3.domain.user.dto.UserSignupRequest
import org.sparta.todoliststep3.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class UserController(
    val userService: UserService,
) {

    @PostMapping("/login")
    fun login(@RequestBody userLoginRequest: UserLoginRequest):String?{
        return userService.login(userLoginRequest)

    }

    @PostMapping("/signup")
    fun signup(@RequestBody userSignupRequest: UserSignupRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signup(userSignupRequest))
    }
}