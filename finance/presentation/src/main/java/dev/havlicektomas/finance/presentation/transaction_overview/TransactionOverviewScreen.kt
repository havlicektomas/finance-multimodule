package dev.havlicektomas.finance.presentation.transaction_overview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.window.core.layout.WindowWidthSizeClass
import dev.havlicektomas.core.presentation.designsystem.FinancemultimoduleTheme
import dev.havlicektomas.core.presentation.designsystem.components.FinanceAppBar
import dev.havlicektomas.core.presentation.designsystem.components.FinanceAppBarAction
import dev.havlicektomas.core.presentation.designsystem.components.FinanceFloatingActionButton
import dev.havlicektomas.core.presentation.designsystem.components.FinanceScaffold
import dev.havlicektomas.finance.presentation.transaction_overview.component.TransactionOverviewScreenLandscape
import dev.havlicektomas.finance.presentation.transaction_overview.component.TransactionOverviewScreenPortrait
import org.koin.androidx.compose.koinViewModel

@Composable
fun TransactionOverviewScreenRoot(
    viewModel: TransactionOverviewViewModel = koinViewModel(),
    onNewTransactionClick: () -> Unit
) {
    TransactionOverviewScreen(
        state = viewModel.state,
        onAction = { action ->
            if (action is TransactionOverviewAction.OnAddTransactionClick) {
                onNewTransactionClick()
            } else {
                viewModel.onAction(action)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionOverviewScreen(
    state: TransactionOverviewState,
    onAction: (TransactionOverviewAction) -> Unit
) {
    val windowClass = currentWindowAdaptiveInfo().windowSizeClass
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )

    FinanceScaffold(
        topAppBar = {
            FinanceAppBar(
                showBackButton = false,
                title = state.username,
                actions = listOf(
                    FinanceAppBarAction(
                        label = "Settings",
                        icon = Icons.Outlined.Settings,
                        onActionClick = {
                            onAction(TransactionOverviewAction.OnSettingsClick)
                        }
                    )
                ),
                onBackClick = {},
                scrollBehavior = scrollBehavior,
                startContent = null,
            )
        },
        floatingActionButton = {
            FinanceFloatingActionButton {
                onAction(TransactionOverviewAction.OnAddTransactionClick)
            }
        },
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) { innerPadding ->
        if (windowClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
            TransactionOverviewScreenPortrait(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                state = state
            )
        } else {
            TransactionOverviewScreenLandscape(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                state = state
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun TransactionOverviewScreenPreview() {
    FinancemultimoduleTheme {
        TransactionOverviewScreen(
            state = TransactionOverviewState(
                username = "test.user",
                accountBalance = 10000.00,
                largestTransactionAmount = 600.00,
                largestTransactionDate = "Jan 07, 2025",
                largestTransactionTitle = "Adobe",
                thisWeekSum = 2500.00,
                latestTransactions = emptyList()
            ),
            onAction = {}
        )
    }
}