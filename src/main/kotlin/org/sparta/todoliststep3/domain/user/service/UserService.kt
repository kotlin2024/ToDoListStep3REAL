package org.sparta.todoliststep3.domain.user.service

import jakarta.transaction.Transactional
import org.sparta.todoliststep3.domain.user.dto.UserLoginRequest
import org.sparta.todoliststep3.domain.user.dto.UserResponse
import org.sparta.todoliststep3.domain.user.dto.UserSignupRequest
import org.sparta.todoliststep3.domain.user.model.Users
import org.sparta.todoliststep3.domain.user.model.toResponse
import org.sparta.todoliststep3.domain.user.repository.UserRepository
import org.sparta.todoliststep3.infra.security.jwt.JwtTokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

import org.springframework.web.bind.annotation.RequestBody

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider
){

    @Transactional
    fun login(loginRequest: UserLoginRequest):String?{

        val user= userRepository.findByUserEmail(loginRequest.userEmail) ?: throw RuntimeException("해당 이메일 없음")

        if(!passwordEncoder.matches(loginRequest.userPassword,user.userPassword)){
            throw RuntimeException("비번 틀림")
        }


        return jwtTokenProvider.generateToken(loginRequest.userEmail,user.userRole)

    }

    @Transactional
    fun signup(signupRequest: UserSignupRequest): UserResponse {
        if(userRepository.existsByuserEmail(signupRequest.userEmail)){
            throw IllegalArgumentException("User email already exists")
        }
        return userRepository.save(
            Users(
                userEmail = signupRequest.userEmail,
                userPassword = passwordEncoder.encode(signupRequest.userPassword),
                userName = signupRequest.userName,
                userRole = signupRequest.role,
            )
        ).toResponse()
    }
}