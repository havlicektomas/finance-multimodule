package dev.havlicektomas.finance.presentation.transaction_new

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import dev.havlicektomas.core.presentation.designsystem.FinancemultimoduleTheme
import dev.havlicektomas.core.presentation.designsystem.components.FinanceAppBar
import dev.havlicektomas.core.presentation.designsystem.components.FinanceAppBarAction
import dev.havlicektomas.core.presentation.designsystem.components.FinanceScaffold
import org.koin.androidx.compose.koinViewModel

@Composable
fun TransactionNewScreenRoot(
    viewModel: TransactionNewViewModel = koinViewModel()
) {
    TransactionNewScreen(
        state = viewModel.state
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionNewScreen(
    state: TransactionNewState
) {
    FinanceScaffold(
        topAppBar = {
            FinanceAppBar(
                showBackButton = true,
                title = "Create transaction",
                actions = listOf(
                    FinanceAppBarAction(
                        label = "Close",
                        icon = Icons.Default.Close,
                        onActionClick = {}
                    )
                ),
                onBackClick = {},
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            //
        }
    }
}

@PreviewLightDark
@Composable
private fun TransactionNewScreenPreview() {
    FinancemultimoduleTheme {
        TransactionNewScreen(
            state = TransactionNewState()
        )
    }
}