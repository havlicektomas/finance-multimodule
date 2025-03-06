package dev.havlicektomas.core.presentation.designsystem.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FinanceScaffold(
    modifier: Modifier = Modifier,
    topAppBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = topAppBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        content(innerPadding)
    }
}