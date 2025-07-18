package com.example.kotlin_spring_project.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.JwtException
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.Date
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Component
class JwtTokenUtil(jwtProperties: JwtProperties) {

    private val secretKey: SecretKey
    private val expiration: Long

    init {
        val secretString = jwtProperties.secret
        this.secretKey = SecretKeySpec(secretString.toByteArray(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.jcaName)
        this.expiration = jwtProperties.expiration
    }

    fun extractUsername(token: String): String =
        extractClaim(token) { it.subject }

    fun extractExpiration(token: String): Date =
        extractClaim(token) { it.expiration }

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        return try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .body
            claimsResolver(claims)
        } catch (e: JwtException) {
            throw IllegalArgumentException("Invalid or expired JWT token", e)
        }
    }

    fun isTokenExpired(token: String): Boolean =
        extractExpiration(token).before(Date())

    fun generateToken(username: String): String =
        Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(secretKey)
            .compact()

    fun validateToken(token: String): Boolean =
        !isTokenExpired(token)
}
