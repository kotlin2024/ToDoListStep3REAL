package org.sparta.todoliststep3.domain.user.ouath.common

import org.springframework.stereotype.Component

@Component
class JwtHelper {
    fun generateAccessToken(id: Long):String {

        return "sample_jwt_token"
    }
}