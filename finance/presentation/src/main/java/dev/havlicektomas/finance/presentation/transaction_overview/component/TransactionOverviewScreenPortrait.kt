package dev.havlicektomas.finance.presentation.transaction_overview.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.havlicektomas.finance.presentation.transaction_overview.TransactionOverviewState

@Composable
fun TransactionOverviewScreenPortrait(
    state: TransactionOverviewState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        TransactionOverViewHeader(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            accountBalance = "$${state.accountBalance}",
            thisWeekTransactionSum = "$${state.thisWeekSum}",
            largestTransactionTitle = state.largestTransactionTitle,
            largestTransactionAmount = "$${state.largestTransactionAmount}",
            largestTransactionDate = state.largestTransactionDate
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(MaterialTheme.colorScheme.background),
        ) {
            TransactionOverviewContent(
                labeledTransaction = state.latestTransactions
            )
        }
    }
}