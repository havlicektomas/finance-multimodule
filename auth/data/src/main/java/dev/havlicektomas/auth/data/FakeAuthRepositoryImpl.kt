package dev.havlicektomas.auth.data

import dev.havlicektomas.auth.domain.AuthRepository
import dev.havlicektomas.core.domain.AuthInfo
import dev.havlicektomas.core.domain.SessionStorage
import dev.havlicektomas.core.domain.util.DataError
import dev.havlicektomas.core.domain.util.EmptyResult
import dev.havlicektomas.core.domain.util.Result
import dev.havlicektomas.core.domain.util.asEmptyDataResult
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class FakeAuthRepositoryImpl(
    private val sessionStorage: SessionStorage
): AuthRepository {

    override suspend fun login(email: String, password: String): EmptyResult<DataError.Network> {
        delay(1.seconds)
        sessionStorage.set(
            AuthInfo(
                accessToken = "test-access-token",
                refreshToken = "test-refresh-token",
                userId = "testUserId"
            )
        )
        return Result.Success(Unit).asEmptyDataResult()
    }

    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
        delay(1.seconds)
        return Result.Success(Unit).asEmptyDataResult()
    }
}