package dev.havlicektomas.auth.data

import dev.havlicektomas.auth.domain.AuthRepository
import dev.havlicektomas.core.data.networking.post
import dev.havlicektomas.core.domain.util.DataError
import dev.havlicektomas.core.domain.util.EmptyResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient
): AuthRepository {

    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {

        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(
                email = email,
                password = password
            )
        )
    }
}