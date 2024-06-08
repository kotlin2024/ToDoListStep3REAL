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
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
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

        logger.debug("Extracted token: $pureToken")

        if(pureToken!=null){
            logger.debug(" $pureToken  현재 puretoken 값")
            jwtTokenProvider.validateToken(pureToken)
                .onSuccess {
                    val userEmail=it.payload.subject
                    val userRole=it.payload.get("userRole",String::class.java)

                    logger.debug("User email: $userEmail, User role: $userRole")

                    val userPrincipal= UserPrincipal(userRole,setOf(userEmail))
                    val authentication = JwtAuthenticationToken(userPrincipal, WebAuthenticationDetailsSource().buildDetails(request))

                    SecurityContextHolder.getContext().authentication = authentication
                }.onFailure {
                    logger.debug("Token validation failed", it)
                }
        }
        logger.debug("지금 토큰이 NULL인 상태라 IF문을 못들어옴 $pureToken")
        filterChain.doFilter(request, response)


    }

}