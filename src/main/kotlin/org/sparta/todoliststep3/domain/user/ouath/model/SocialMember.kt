package org.sparta.todoliststep3.domain.user.ouath.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.*

@Entity
class SocialMember (

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="social_member_id")
    var id: Long? = null,

    val providerName:String, // 네이버,카카오
    val providerId:String,
    var nickname:String,
){

}