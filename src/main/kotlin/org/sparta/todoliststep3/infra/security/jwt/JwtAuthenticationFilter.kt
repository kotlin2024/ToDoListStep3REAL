package org.sparta.todoliststep3.infra.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.sparta.todoliststep3.infra.security.JwtAuthenticationToken
import org.sparta.todoliststep3.infra.security.UserPrincipal
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider,
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        var pureToken:String?=null
        if (request.getHeader(AUTHORIZATION) != null && request.getHeader(AUTHORIZATION).startsWith("Bearer ")) { // Bearer로 시작하면은 Bearer를 제거하고 순수 토큰 문자열을 반환
            pureToken=request.getHeader(AUTHORIZATION).substring(7)
        }

        if(pureToken!=null){
            jwtTokenProvider.validateToken(pureToken)
                .onSuccess {
                    val userEmail=it.payload.subject
                    val userRole=it.payload.get("role",String::class.java)

                    val userPrincipal= UserPrincipal(userRole,setOf(userEmail))
                    val authentication = JwtAuthenticationToken(userPrincipal, WebAuthenticationDetailsSource().buildDetails(request))

                    SecurityContextHolder.getContext().authentication = authentication
                }
        }
        filterChain.doFilter(request, response)


    }

}