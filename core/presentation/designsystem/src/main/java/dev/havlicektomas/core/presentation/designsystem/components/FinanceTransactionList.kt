package dev.havlicektomas.core.presentation.designsystem.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import dev.havlicektomas.core.domain.finance.FinanceTransaction
import dev.havlicektomas.core.domain.finance.FinanceTransactionCategory
import dev.havlicektomas.core.domain.finance.FinanceTransactionType
import dev.havlicektomas.core.presentation.designsystem.FinancemultimoduleTheme

data class TransactionsPerDay(
    val date: String,
    val transactions: List<FinanceTransaction>
)

@Composable
fun FinanceTransactionList(
    modifier: Modifier = Modifier,
    labeledTransactions: List<TransactionsPerDay>
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ) {
        labeledTransactions.forEach { transactionsPerDay ->
            item {
                Text(
                    text = transactionsPerDay.date,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                )
            }
            items(transactionsPerDay.transactions) { transaction ->
                FinanceTransactionListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    transaction = transaction
                )
            }
        }
    }
}

@OptIn(kotlin. uuid. ExperimentalUuidApi::class)
@PreviewLightDark
@Composable
private fun FinanceTransactionListPreview() {
    FinancemultimoduleTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {
            FinanceTransactionList(
                labeledTransactions = listOf(
                    TransactionsPerDay(
                        date = "Today",
                        transactions = listOf(
                            FinanceTransaction(
                                type = FinanceTransactionType.EXPENSE,
                                amount = 158.25,
                                title = "Test",
                                note = "",
                                category = FinanceTransactionCategory.OTHER
                            ),
                            FinanceTransaction(
                                type = FinanceTransactionType.INCOME,
                                amount = 158.25,
                                title = "Test",
                                note = "",
                                category = FinanceTransactionCategory.OTHER
                            ),
                            FinanceTransaction(
                                type = FinanceTransactionType.EXPENSE,
                                amount = 158.25,
                                title = "Test",
                                note = "",
                                category = FinanceTransactionCategory.OTHER
                            )
                        )
                    ),
                    TransactionsPerDay(
                        date = "Yesterday",
                        transactions = listOf(
                            FinanceTransaction(
                                type = FinanceTransactionType.EXPENSE,
                                amount = 158.25,
                                title = "Test",
                                note = "",
                                category = FinanceTransactionCategory.OTHER
                            ),
                            FinanceTransaction(
                                type = FinanceTransactionType.INCOME,
                                amount = 158.25,
                                title = "Test",
                                note = "",
                                category = FinanceTransactionCategory.OTHER
                            )
                        )
                    )
                )
            )
        }
    }
}