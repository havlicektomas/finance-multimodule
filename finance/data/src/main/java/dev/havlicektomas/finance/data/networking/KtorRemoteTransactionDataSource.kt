package dev.havlicektomas.finance.data.networking

import dev.havlicektomas.core.data.networking.delete
import dev.havlicektomas.core.data.networking.dto.TransactionDto
import dev.havlicektomas.core.data.networking.get
import dev.havlicektomas.core.data.networking.mapper.toTransaction
import dev.havlicektomas.core.data.networking.post
import dev.havlicektomas.core.domain.finance.FinanceTransaction
import dev.havlicektomas.core.domain.finance.RemoteTransactionDataSource
import dev.havlicektomas.core.domain.util.DataError
import dev.havlicektomas.core.domain.util.EmptyResult
import dev.havlicektomas.core.domain.util.Result
import dev.havlicektomas.core.domain.util.asEmptyDataResult
import dev.havlicektomas.core.domain.util.map
import dev.havlicektomas.finance.data.networking.mapper.toCreateRequest
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.authProviders
import io.ktor.client.plugins.auth.providers.BearerAuthProvider

class KtorRemoteTransactionDataSource(
    private val httpClient: HttpClient,
): RemoteTransactionDataSource {

    override suspend fun getTransactions(): Result<List<FinanceTransaction>, DataError.Network> {
        return httpClient.get<List<TransactionDto>>(
            route = "/transactions"
        ).map { transactionDtos ->
            transactionDtos.map { it.toTransaction() }
        }
    }

    override suspend fun postTransaction(transaction: FinanceTransaction): Result<FinanceTransaction, DataError.Network> {
        return httpClient.post<CreateTransactionRequest, TransactionDto>(
            route = "/transaction",
            body = transaction.toCreateRequest()
        ).map { it.toTransaction() }
    }

    override suspend fun deleteTransaction(id: String): EmptyResult<DataError.Network> {
        return httpClient.delete(
            route = "/transaction",
            queryParameters = mapOf(
                "id" to id
            )
        )
    }

    override suspend fun logout(): EmptyResult<DataError.Network> {
        val result = httpClient.get<Unit>(
            route = "/logout"
        ).asEmptyDataResult()

        httpClient.authProviders.filterIsInstance<BearerAuthProvider>()
            .firstOrNull()
            ?.clearToken()

        return result
    }
}