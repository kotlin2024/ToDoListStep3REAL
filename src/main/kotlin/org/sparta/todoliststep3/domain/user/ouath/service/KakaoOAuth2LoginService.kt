package org.sparta.todoliststep3.domain.user.ouath.service

import org.sparta.todoliststep3.domain.user.ouath.client.KakaoOAuth2LoginClient
import org.sparta.todoliststep3.domain.user.ouath.common.JwtHelper
import org.sparta.todoliststep3.infra.security.jwt.JwtTokenProvider
import org.springframework.stereotype.Service


@Service
class KakaoOAuth2LoginService(
    private val kakaoOAuth2LoginClient: KakaoOAuth2LoginClient,
    private val socialMemberService: SocialMemberService,
    private val jwtHelper: JwtHelper,
    private val jwtTokenProvider: JwtTokenProvider
) {
    fun generateLoginPageUrl(): String {
        // 로그인 페이지에 대한 URL 생성 및 반환
        return kakaoOAuth2LoginClient.generateLoginPageUrl()
    }


    fun login(code: String): String? {
        // code를 통해 AccessToken 발급
        return kakaoOAuth2LoginClient.getAccessToken(code)

        // AccessToken을 통해서 사용자 정보 조회
            .let{kakaoOAuth2LoginClient.retrieveUserInfo(it)}

        // 사용자 정보로 우리쪽 회원가임 & 조회(registerIfAbsent)
            .let{socialMemberService.registerIfAbsent(it)}

        // 우리쪽 AccessToken을 만들어서 반환
            .let{jwtTokenProvider.generateToken(userEmail = it.providerId, userRole = "NORMAL")}
    //.let{jwtHelper.generateAccessToken(it.id!!)}
    }
}