package org.sparta.todoliststep3.domain.user.ouath.service

import org.sparta.todoliststep3.domain.user.ouath.dto.KakaoLoginUserInfoResponse
import org.sparta.todoliststep3.domain.user.ouath.model.SocialMember
import org.sparta.todoliststep3.domain.user.ouath.repository.SocialMemberRepository
import org.springframework.stereotype.Service

@Service
class SocialMemberService(
    private val socialMemberRepository: SocialMemberRepository)
{
    fun registerIfAbsent(userInfo: KakaoLoginUserInfoResponse): SocialMember {
        // 1. 일단 사용자 정보가 있는지 조회

        return socialMemberRepository.findByProviderNameAndProviderId("KAKAO",userInfo.id.toString())
            ?:socialMemberRepository.save(
                SocialMember(
                    providerName = "KAKAO",
                    providerId=userInfo.id.toString(),
                    nickname = userInfo.properties.nickname
                )
            )
        //2. 없으면 만들어서 반환
    }
}