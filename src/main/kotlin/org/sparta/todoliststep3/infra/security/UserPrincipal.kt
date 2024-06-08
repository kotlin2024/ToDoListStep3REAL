package org.sparta.todoliststep3.infra.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserPrincipal(
    val userEmail:String,
    val authorities:Collection<GrantedAuthority>,
)
{
    constructor(userEmail:String,userRole:Set<String>):this(
        userEmail,
        userRole.map{ SimpleGrantedAuthority("ROLE_$it") }
    )
}