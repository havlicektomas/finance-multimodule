package dev.havlicektomas.finance.presentation.transaction_overview.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.havlicektomas.core.domain.finance.FinanceTransactionCategory
import dev.havlicektomas.core.domain.finance.FinanceTransactionType
import dev.havlicektomas.core.presentation.designsystem.FinancemultimoduleTheme
import dev.havlicektomas.core.presentation.ui.UiText
import dev.havlicektomas.finance.presentation.R
import dev.havlicektomas.finance.presentation.components.FinanceTransactionList
import dev.havlicektomas.finance.presentation.model.TransactionsPerDay
import dev.havlicektomas.finance.presentation.model.UITransaction

@Composable
fun BoxScope.TransactionOverviewContent(
    modifier: Modifier = Modifier,
    labeledTransaction: List<TransactionsPerDay>
) {
    if (labeledTransaction.isEmpty()) {
        Text(
            text = "No transactions",
            modifier = modifier
                .align(Alignment.Center)
        )
    } else {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Latest transactions",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Show All",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            FinanceTransactionList(
                modifier = Modifier
                    .fillMaxSize(),
                labeledTransactions = labeledTransaction
            )
        }
    }
}

@OptIn(kotlin. uuid. ExperimentalUuidApi::class)
@PreviewLightDark
@Composable
private fun TransactionOverviewContentPreview() {
    FinancemultimoduleTheme {
        Box(
            modifier = Modifier
        ) {
            TransactionOverviewContent(
                modifier = Modifier.fillMaxSize(),
                labeledTransaction = listOf(
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
                            ),
                        )
                    )
                )
            )
        }
    }
}