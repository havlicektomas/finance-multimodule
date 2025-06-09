package dev.havlicektomas.finance.presentation.components

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
import androidx.compose.ui.unit.sp
import dev.havlicektomas.core.domain.finance.FinanceTransactionCategory
import dev.havlicektomas.core.domain.finance.FinanceTransactionType
import dev.havlicektomas.core.presentation.designsystem.FinancemultimoduleTheme
import dev.havlicektomas.core.presentation.ui.UiText
import dev.havlicektomas.finance.presentation.R
import dev.havlicektomas.finance.presentation.model.TransactionsPerDay
import dev.havlicektomas.finance.presentation.model.UITransaction

@Composable
fun FinanceTransactionList(
    modifier: Modifier = Modifier,
    labeledTransactions: List<TransactionsPerDay>
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        labeledTransactions.forEach { transactionsPerDay ->
            item {
                Text(
                    text = transactionsPerDay.date.asString(),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
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
                        date = UiText.StringResource(R.string.today),
                        transactions = listOf(
                            UITransaction(
                                type = FinanceTransactionType.EXPENSE,
                                amount = "158.25",
                                title = "Test",
                                note = "",
                                category = FinanceTransactionCategory.OTHER,
                                id = "",
                                timestamp = ""
                            ),
                            UITransaction(
                                type = FinanceTransactionType.INCOME,
                                amount = "158.25",
                                title = "Test",
                                note = "",
                                category = FinanceTransactionCategory.OTHER,
                                id = "",
                                timestamp = ""
                            ),
                            UITransaction(
                                type = FinanceTransactionType.EXPENSE,
                                amount = "158.25",
                                title = "Test",
                                note = "",
                                category = FinanceTransactionCategory.OTHER,
                                id = "",
                                timestamp = ""
                            )
                        )
                    ),
                    TransactionsPerDay(
                        date = UiText.StringResource(R.string.yesterday),
                        transactions = listOf(
                            UITransaction(
                                type = FinanceTransactionType.EXPENSE,
                                amount = "158.25",
                                title = "Test",
                                note = "",
                                category = FinanceTransactionCategory.OTHER,
                                id = "",
                                timestamp = ""
                            ),
                            UITransaction(
                                type = FinanceTransactionType.INCOME,
                                amount = "158.25",
                                title = "Test",
                                note = "",
                                category = FinanceTransactionCategory.OTHER,
                                id = "",
                                timestamp = ""
                            )
                        )
                    )
                )
            )
        }
    }
}