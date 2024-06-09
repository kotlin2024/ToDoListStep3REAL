package org.sparta.todoliststep3.domain.user.ouath.repository

import org.sparta.todoliststep3.domain.user.ouath.model.SocialMember
import org.springframework.data.jpa.repository.JpaRepository

interface SocialMemberRepository: JpaRepository<SocialMember,Long> {
    fun findByProviderNameAndProviderId(providerName: String, providerId: String): SocialMember?
}