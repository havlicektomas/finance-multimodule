package dev.havlicektomas.core.domain.auth

data class AuthInfo(
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)
