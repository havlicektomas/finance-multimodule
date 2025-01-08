package dev.havlicektomas.auth.domain

import dev.havlicektomas.core.domain.util.DataError
import dev.havlicektomas.core.domain.util.EmptyResult

interface AuthRepository {
    suspend fun register(email: String, password: String): EmptyResult<DataError.Network>
}