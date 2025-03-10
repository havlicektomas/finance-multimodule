package dev.havlicektomas.finance.presentation.transaction_overview.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.havlicektomas.core.presentation.designsystem.FinancemultimoduleTheme

@Composable
fun TransactionOverViewHeader(
    modifier: Modifier = Modifier,
    accountBalance: String,
    thisWeekTransactionSum: String,
    largestTransactionTitle: String,
    largestTransactionAmount: String,
    largestTransactionDate: String
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = accountBalance,
                fontSize = 36.sp
            )
            Text(
                text = "Account Balance",
                fontSize = 12.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = MaterialTheme.colorScheme.background)
                    .weight(2f)
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = largestTransactionTitle,
                        fontSize = 18.sp
                    )
                    Text(
                        text = largestTransactionAmount,
                        fontSize = 18.sp
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Largest transaction",
                        fontSize = 12.sp
                    )
                    Text(
                        text = largestTransactionDate,
                        fontSize = 12.sp
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = MaterialTheme.colorScheme.tertiary)
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text(
                    text = thisWeekTransactionSum,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onTertiary
                )
                Text(
                    text = "This week",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun TransactionOverViewHeaderPreview() {
    FinancemultimoduleTheme {
        Surface(
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            TransactionOverViewHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                accountBalance = "$10.382.45",
                thisWeekTransactionSum = "-$762.20",
                largestTransactionTitle = "Adobe",
                largestTransactionAmount = "-$59.99",
                largestTransactionDate = "Jan 7, 2025"
            )
        }
    }
}