package dev.havlicektomas.finance.presentation.transaction_overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import dev.havlicektomas.core.presentation.designsystem.FinancemultimoduleTheme
import dev.havlicektomas.core.presentation.designsystem.components.FinanceAppBar
import dev.havlicektomas.core.presentation.designsystem.components.FinanceAppBarAction
import dev.havlicektomas.core.presentation.designsystem.components.FinanceFloatingActionButton
import dev.havlicektomas.core.presentation.designsystem.components.FinanceScaffold
import dev.havlicektomas.finance.presentation.transaction_overview.component.TransactionOverViewHeader
import org.koin.androidx.compose.koinViewModel

@Composable
fun TransactionOverviewScreenRoot(
    viewModel: TransactionOverviewViewModel = koinViewModel(),
    onNewTransactionClick: () -> Unit
) {
    TransactionOverviewScreen(
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
                title = "username",
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
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
            ) {
                TransactionOverViewHeader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f),
                    accountBalance = "$10.382.45",
                    thisWeekTransactionSum = "-$762.20",
                    largestTransactionTitle = "Adobe",
                    largestTransactionAmount = "-$59.99",
                    largestTransactionDate = "Jan 7, 2025"
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    Text("transactions placeholder")
                }
            }
        } else {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
            ) {
                TransactionOverViewHeader(
                    modifier = Modifier
                        .weight(2f),
                    accountBalance = "$10.382.45",
                    thisWeekTransactionSum = "-$762.20",
                    largestTransactionTitle = "Adobe",
                    largestTransactionAmount = "-$59.99",
                    largestTransactionDate = "Jan 7, 2025"
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(3f)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    Text("transactions placeholder")
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun TransactionOverviewScreenPreview() {
    FinancemultimoduleTheme {
        TransactionOverviewScreen(
            onAction = {}
        )
    }
}