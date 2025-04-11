package dev.havlicektomas.finance.presentation.transaction_overview.component

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.havlicektomas.core.presentation.designsystem.components.FinanceTransactionList
import dev.havlicektomas.core.presentation.designsystem.components.TransactionsPerDay

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
        FinanceTransactionList(
            modifier = modifier,
            labeledTransactions = emptyList()
        )
    }
}