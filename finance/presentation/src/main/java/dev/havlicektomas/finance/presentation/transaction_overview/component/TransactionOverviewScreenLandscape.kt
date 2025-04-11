package dev.havlicektomas.finance.presentation.transaction_overview.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.havlicektomas.finance.presentation.transaction_overview.TransactionOverviewState

@Composable
fun TransactionOverviewScreenLandscape(
    state: TransactionOverviewState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        TransactionOverViewHeader(
            modifier = Modifier
                .weight(2f),
            accountBalance = "$${state.accountBalance}",
            thisWeekTransactionSum = "$${state.thisWeekSum}",
            largestTransactionTitle = state.largestTransactionTitle,
            largestTransactionAmount = "$${state.largestTransactionAmount}",
            largestTransactionDate = state.largestTransactionDate
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(3f)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            TransactionOverviewContent(
                labeledTransaction = state.latestTransactions
            )
        }
    }
}