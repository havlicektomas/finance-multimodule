package dev.havlicektomas.finance.presentation.transaction_new

import dev.havlicektomas.core.domain.finance.FinanceTransaction
import kotlin.uuid.ExperimentalUuidApi

data class TransactionNewState @OptIn(ExperimentalUuidApi::class) constructor(
    val transaction: FinanceTransaction = FinanceTransaction(),
    val isSavingTransaction: Boolean = false,
    val canCreateTransaction: Boolean = false
)
