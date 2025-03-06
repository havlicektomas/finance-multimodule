package dev.havlicektomas.finance.presentation.transaction_overview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import dev.havlicektomas.core.presentation.designsystem.FinancemultimoduleTheme
import dev.havlicektomas.core.presentation.designsystem.components.FinanceAppBar
import dev.havlicektomas.core.presentation.designsystem.components.FinanceAppBarAction
import dev.havlicektomas.core.presentation.designsystem.components.FinanceFloatingActionButton
import dev.havlicektomas.core.presentation.designsystem.components.FinanceScaffold
import org.koin.androidx.compose.koinViewModel

@Composable
fun TransactionOverviewScreenRoot(
    viewModel: TransactionOverviewViewModel = koinViewModel()
) {
    TransactionOverviewScreen(
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionOverviewScreen(
    onAction: (TransactionOverviewAction) -> Unit
) {
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
                startContent = null
            )
        },
        floatingActionButton = {
            FinanceFloatingActionButton {
                onAction(TransactionOverviewAction.OnAddTransactionClick)
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        )
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