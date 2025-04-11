package dev.havlicektomas.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.havlicektomas.core.domain.finance.FinanceTransaction
import dev.havlicektomas.core.domain.finance.FinanceTransactionCategory
import dev.havlicektomas.core.domain.finance.FinanceTransactionType
import dev.havlicektomas.core.presentation.designsystem.ArrowLeftIcon
import dev.havlicektomas.core.presentation.designsystem.ArrowRightIcon
import dev.havlicektomas.core.presentation.designsystem.FinancemultimoduleTheme

@Composable
fun FinanceTransactionListItem(
    modifier: Modifier = Modifier,
    transaction: FinanceTransaction
) {
    Row(
        modifier = modifier
    ) {
        Icon(
            imageVector = if (transaction.type == FinanceTransactionType.INCOME) {
                ArrowRightIcon
            } else {
                ArrowLeftIcon
            },
            contentDescription = if (transaction.type == FinanceTransactionType.INCOME) {
                "Income"
            } else {
                "Expense"
            },
            tint = if (transaction.type == FinanceTransactionType.INCOME) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.error
            },
            modifier = Modifier
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = transaction.title,
                fontSize = 16.sp
            )
            Text(
                text = transaction.category.name.lowercase(),
                fontSize = 12.sp
            )
            if (transaction.note.isNotBlank()) {
                Text(
                    text = transaction.note,
                    fontSize = 12.sp
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        val currency = if (transaction.type == FinanceTransactionType.EXPENSE) "-$" else "$"
        Text(
            text = "$currency${transaction.amount}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = if (transaction.type == FinanceTransactionType.INCOME) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onBackground
            },
            modifier = Modifier
        )
    }
}

@OptIn(kotlin. uuid. ExperimentalUuidApi::class)
@PreviewLightDark
@Composable
private fun TransactionListItemExpensePreview() {
    FinancemultimoduleTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {
            FinanceTransactionListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                transaction = FinanceTransaction(
                    type = FinanceTransactionType.EXPENSE,
                    amount = 158.25,
                    title = "Test",
                    note = "Test note",
                    category = FinanceTransactionCategory.OTHER
                )
            )
        }
    }
}

@OptIn(kotlin. uuid. ExperimentalUuidApi::class)
@PreviewLightDark
@Composable
private fun TransactionListItemIncomePreview() {
    FinancemultimoduleTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {
            FinanceTransactionListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                transaction = FinanceTransaction(
                    type = FinanceTransactionType.INCOME,
                    amount = 158.25,
                    title = "Test",
                    note = "Test note",
                    category = FinanceTransactionCategory.OTHER
                )
            )
        }
    }
}