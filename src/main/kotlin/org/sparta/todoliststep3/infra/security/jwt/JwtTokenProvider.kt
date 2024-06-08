package org.sparta.todoliststep3.infra.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.util.*

@Component
class JwtTokenProvider(
    @Value("\${auth.jwt.issuer}") private val issuer:String,
    @Value("\${auth.jwt.secret}") private val secret:String,
    @Value("\${auth.jwt.validity}") private val validityInMilliseconds:Long,
){

    fun generateToken(userEmail:String,userRole:String):String{

        val claims = Jwts.claims().add(mapOf("userRole" to userRole,"userEmail" to userEmail)).build()

        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
        val now= Date()
        val validity=Date(now.time + validityInMilliseconds)

        return Jwts.builder().subject(userEmail).issuer(issuer).expiration(validity).claims(claims).signWith(key).compact()
    }

    fun validateToken(token:String?): Result<Jws<Claims>>{

        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))

            Jwts.parser().verifyWith(key).build().parseSignedClaims(token)
        }
    }


}