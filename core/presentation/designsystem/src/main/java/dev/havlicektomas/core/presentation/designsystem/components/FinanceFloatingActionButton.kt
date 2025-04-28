package dev.havlicektomas.core.presentation.designsystem.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import dev.havlicektomas.core.presentation.designsystem.FinancemultimoduleTheme

@Composable
fun FinanceFloatingActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        shape = CircleShape,
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = null
        )
    }
}

@PreviewLightDark
@Composable
private fun FinanceFloatingActionButtonPreview() {
    FinancemultimoduleTheme {
        FinanceFloatingActionButton(
            onClick = {}
        )
    }
}