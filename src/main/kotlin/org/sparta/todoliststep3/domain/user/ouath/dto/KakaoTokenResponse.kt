package org.sparta.todoliststep3.domain.user.ouath.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming


@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class KakaoTokenResponse(
    val accessToken: String
)
