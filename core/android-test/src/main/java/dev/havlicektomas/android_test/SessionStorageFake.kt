package dev.havlicektomas.android_test

import dev.havlicektomas.core.domain.auth.AuthInfo
import dev.havlicektomas.core.domain.auth.SessionStorage

class SessionStorageFake: SessionStorage {

    private var authInfo: AuthInfo? = null

    override suspend fun get(): AuthInfo? {
        return authInfo
    }

    override suspend fun set(info: AuthInfo?) {
        authInfo = info
    }
}