package dev.havlicektomas.finance.presentation.mapper

import android.text.format.DateUtils.isToday
import dev.havlicektomas.core.domain.finance.FinanceTransaction
import dev.havlicektomas.core.presentation.ui.UiText
import dev.havlicektomas.core.presentation.ui.getFormattedLocalDate
import dev.havlicektomas.core.presentation.ui.isToday
import dev.havlicektomas.finance.presentation.R
import dev.havlicektomas.finance.presentation.model.TransactionsPerDay
import dev.havlicektomas.finance.presentation.model.UITransaction
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun UITransaction.toDomainTransaction(): FinanceTransaction {
    return FinanceTransaction(
        id = this.id,
        type = this.type,
        title = this.title,
        amount = this.amount.toDouble(),
        note = this.note,
        category = this.category,
        timestamp = if (this.timestamp.isEmpty()) {
            ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC"))
        } else {
            ZonedDateTime.parse(this.timestamp)
        }
    )
}

fun FinanceTransaction.toUITransaction(): UITransaction {
    return UITransaction(
        id = this.id,
        type = this.type,
        title = this.title,
        amount = String.format(Locale.getDefault(), "%.2f", this.amount),
        note = this.note,
        category = this.category,
        timestamp = this.timestamp.format(DateTimeFormatter.ISO_DATE_TIME)
    )
}

fun financeTransactionsToTransactionsPerDay(transactions: List<FinanceTransaction>): List<TransactionsPerDay> {
    val sortedTransactions = transactions.groupBy { transaction ->
        transaction.timestamp.toLocalDate()
    }.toSortedMap()

    return sortedTransactions.map { item ->
        TransactionsPerDay(
            date = when {
                item.key.isToday() -> UiText.StringResource(R.string.today)
                item.key.minusDays(1).isToday() -> UiText.StringResource(R.string.yesterday)
                else -> UiText.DynamicString(item.key.getFormattedLocalDate())
            },
            transactions = item.value.map { transaction ->
                transaction.toUITransaction()
            }
        )
    }
}